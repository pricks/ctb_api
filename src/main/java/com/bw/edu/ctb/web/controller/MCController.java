package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.Keys;
import com.bw.edu.ctb.common.enums.DlEnum;
import com.bw.edu.ctb.common.enums.SortEnum;
import com.bw.edu.ctb.common.enums.subjects.DGRel;
import com.bw.edu.ctb.common.enums.subjects.DagangEnum;
import com.bw.edu.ctb.common.qo.ExRecQO;
import com.bw.edu.ctb.common.qo.ExSttByclQO;
import com.bw.edu.ctb.common.qo.TTBactchQO;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.common.util.CollectionUtil;
import com.bw.edu.ctb.common.util.JacksonUtil;
import com.bw.edu.ctb.common.util.StringUtil;
import com.bw.edu.ctb.dao.entity.*;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.domain.*;
import com.bw.edu.ctb.dto.GCDTO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.service.ExRecService;
import com.bw.edu.ctb.service.ExSttService;
import com.bw.edu.ctb.service.TTService;
import com.bw.edu.ctb.service.UnitService;
import com.bw.edu.ctb.service.usr.UsrService;
import com.bw.edu.ctb.web.controller.common.TTBuilder;
import com.bw.edu.ctb.web.vo.LatestUnitVO;
import com.bw.edu.ctb.web.vo.UnitVO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

@RestController
@RequestMapping("/mc")
public class MCController {
    private final static Logger logger = LoggerFactory.getLogger(MCController.class);

    @Autowired
    private UnitService unitService;
    @Autowired
    private ExSttService exSttService;
    @Autowired
    private TTService ttService;
    @Autowired
    private UsrService usrService;
    @Autowired
    private ExRecService exRecService;

    /** get user's latest unit exercise record */
    @PostMapping("glu")
    public Result mcctl_glu(UnitQO unitQO, HttpServletRequest request){
        try{
            logger.error("glu. r=" + request.getRemoteAddr());

            //verify
            if(null==unitQO || StringUtil.isEmpty(unitQO.getAtk())
                    || null==unitQO.getGd() || null==unitQO.getDg()
                    || null==unitQO.getCl()){
                return Result.failure();
            }
            Result<BUsr> bUsrRS = usrService.getByAtk(unitQO.getAtk());
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }

            Long un = unitQO.getUn();
            if(null==un){
                Result<Long> unRS = exRecService.selectLatestExrByCl(new ExRecQO(bUsr.getId(), unitQO.getCl()));
                if(!unRS.isSuccess() || null==unRS.getData()){
                    //获取class下第一个unit，即code最小的那个
                    unitQO.setSortProperty("code");
                    unitQO.setSortMode(SortEnum.ASC.getMode());
                    Result<List<UnitEntity>> unsRS = unitService.queryByCl(unitQO);
                    if(!unsRS.isSuccess() || CollectionUtil.isEmpty(unsRS.getData())){
                        return Result.failure("no unit","还没有录入单元信息");
                    }
                    return Result.success(LatestUnitVO.build(unsRS.getData().get(0)));
                }
                un = unRS.getData();
            }

            unitQO.setUn(un);
            //查询课程下的所有单元统计信息
            Result<SttClDO> exSttByclRS = getExSttBycl(unitQO, bUsr);
            if(!exSttByclRS.isSuccess()){
                //1.如果没有查到历史练习统计信息
                return queryUnit(un);
            }

            //2.如果查询到了历史练习统计信息
            SttClDO sttClDO = exSttByclRS.getData();
            SttDlDO sttDlDO = sttClDO.getBasicDl(un);
            if(null==sttDlDO){
                //2.1.如果有课程的历史练习统计信息，但是没有单元的历史统计
                return queryUnit(un);
            }
            LatestUnitVO luv = new LatestUnitVO();
            luv.setUn(un);
            for(SttUnDO su : sttClDO.getUns()){
                if(su.getUn().equals(un)){
                    luv.setUname(su.getUname());
                }
            }
            luv.setRd(sttDlDO.getRd());
            luv.setEc(sttDlDO.getEc());
            return Result.success(luv);

        }catch (CtbException e){
            logger.error("glu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("glu sys-error. unitQO="+unitQO, e);
            return Result.failure();
        }
    }

    private Result queryUnit(Long un) {
        Result<UnitEntity> unitEntityResult = unitService.getByCode(un);
        if (unitEntityResult == null || !unitEntityResult.isSuccess() || null == unitEntityResult.getData()) {
            throw new CtbException(CtbExceptionEnum.UNIT_IS_NULL);
        }
        return Result.success(LatestUnitVO.build(unitEntityResult.getData()));
    }

    /** get user's titles by unit */
    @PostMapping("gtbu")
    public Result mcctl_gtbu(UnitQO unitQO, HttpServletRequest request){
        try{
            logger.error("glu. r=" + request.getRemoteAddr());

            //verify
            if(null==unitQO || StringUtil.isEmpty(unitQO.getAtk())
                    || null==unitQO.getGd() || null==unitQO.getDg()
                    || null==unitQO.getCl() || null==unitQO.getUn()){
                return Result.failure();
            }
            Result<BUsr> bUsrRS = usrService.getByAtk(unitQO.getAtk());
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }

            //查询课程下的所有单元统计信息
            Result<SttClDO> exSttByclRS = getExSttBycl(unitQO, bUsr);
            if(!exSttByclRS.isSuccess()){
                return exSttByclRS;
            }
            SttClDO sttClDO = exSttByclRS.getData();
            SttDlDO sttDlDO = sttClDO.getBasicDl(unitQO.getUn());
            if(null==sttDlDO){
                sttDlDO = new SttDlDO();
                sttDlDO.setDl(DlEnum.BASIC.getCode());
            }

            //查询tts
            TTBactchQO ttBactchQO = new TTBactchQO();
            ttBactchQO.setUid(bUsr.getId());
            ttBactchQO.setAtk(unitQO.getAtk());
            ttBactchQO.setUn(unitQO.getUn());
            ttBactchQO.setDl(sttDlDO.getDl());
            ttBactchQO.setEok(1);
            ttBactchQO.setRd(sttDlDO.getRd());
            if(null==sttDlDO.getEc() || null==sttDlDO.getRd()){
                ttBactchQO.setRd(0);
            }else{
                ttBactchQO.setRl((sttDlDO.getEc()==0&&sttDlDO.getRd()>1)?1:0);
            }
            ttBactchQO.setMaxKpId(sttDlDO.getMaxKpId());
            ttBactchQO.setMaxTid(sttDlDO.getMaxTid());
            return getTts(ttBactchQO);
        }catch (CtbException e){
            logger.error("glu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("glu sys-error. unitQO="+unitQO, e);
            return Result.failure();
        }
    }


    /** get user's unit ex_stt */
    @PostMapping("gu")
    public Result mcctl_gu(UnitQO unitQO, HttpServletRequest request){
        try{
            logger.error("gu. r=" + request.getRemoteAddr());

            //verify
            if(null==unitQO || StringUtil.isEmpty(unitQO.getAtk())
                    || null==unitQO.getGd() || null==unitQO.getDg()
                    || null==unitQO.getCl()){
                return Result.failure();
            }
            Result<BUsr> bUsrRS = usrService.getByAtk(unitQO.getAtk());
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }

            return getExSttBycl(unitQO, bUsr);
        }catch (CtbException e){
            logger.error("gu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("gu sys-error. unitQO="+unitQO, e);
            return Result.failure();
        }
    }

    private Result<SttClDO> getExSttBycl(UnitQO unitQO, BUsr bUsr) throws Exception {
        Result<List<UnitEntity>> unitRs = unitService.queryByCl(unitQO);
        if(!unitRs.isSuccess()){
            promoteException(unitRs.getCode(), unitRs.getMessage());
        }
        if(CollectionUtil.isEmpty(unitRs.getData())){
            return Result.failure("no unit","the units have not been registered.");
        }

        ExSttByclQO qo = new ExSttByclQO();
        qo.setUid(bUsr.getId());
        qo.setDg(unitQO.getDg());
        qo.setGd(unitQO.getGd());
        qo.setCl(unitQO.getCl());
        Result<ExSttByclEntity> exSttByclEntityResult = exSttService.queryExSttBycl(qo);
        if(null == exSttByclEntityResult.getData()){
            //写表，写入一条空记录
            SttClDO stt = SttClDO.buildEmpty(unitRs.getData(), UnitDO.build(unitQO));
            String sttStr = JacksonUtil.serialize(stt);
            ExSttByclEntity ebe = new ExSttByclEntity();
            ebe.setUid(bUsr.getId());
            ebe.setDg(unitQO.getDg());
            ebe.setGd(unitQO.getGd());
            ebe.setCl(unitQO.getCl());
            ebe.setStt(sttStr);
            Result<Void> rs = exSttService.create(ebe);
            if(!rs.isSuccess()){
                logger.error(String.format("[fatal] create ex_stt_bycl failed. errCode=%s, errMsg=%s", rs.getCode(), rs.getMessage()));
                return Result.failure();
            }

            return Result.success(stt);
        }else{
            if(exSttByclEntityResult.isSuccess()){
                SttClDO stt = JacksonUtil.deserialize(exSttByclEntityResult.getData().getStt(), SttClDO.class);
                return Result.success(stt);
            }else{
                SttClDO stt = SttClDO.buildEmpty(unitRs.getData(), UnitDO.build(unitQO));
                return Result.success(stt);
            }
        }
    }

    /** get user's tts batch */
    @PostMapping("gt")
    public Result mcctl_gt(TTBactchQO ttBactchQO, HttpServletRequest request){
        try{
            logger.error("gt. r=" + request.getRemoteAddr());
            return getTts(ttBactchQO);
        }catch (CtbException e){
            logger.error("gu biz-error. ttBactchQO="+ttBactchQO, e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("gu sys-error. ttBactchQO="+ttBactchQO, e);
            return Result.failure();
        }
    }

    @PostMapping("u")
    public Result query__un(UnitQO unitQO, HttpServletRequest request){
        try{
            logger.error("glu. r=" + request.getRemoteAddr());

            //verify
            if(null==unitQO || StringUtil.isEmpty(unitQO.getAtk())
                    || null==unitQO.getGd() || null==unitQO.getDg()
                    || null==unitQO.getCl()){
                return Result.failure();
            }
            Result<BUsr> bUsrRS = usrService.getByAtk(unitQO.getAtk());
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }

            unitQO.setSortProperty("code");
            unitQO.setSortMode(SortEnum.ASC.getMode());
            Result<List<UnitEntity>> unsRS = unitService.queryByCl(unitQO);
            if(CollectionUtil.isNotEmpty(unsRS.getData())){
                List<UnitVO> uvs = new ArrayList<>(unsRS.getData().size());
                for(UnitEntity ue : unsRS.getData()){
                    UnitVO uv = new UnitVO();
                    uv.setUc(ue.getCode());
                    uv.setUn(ue.getName());
                    uvs.add(uv);
                }
                return Result.success(uvs);
            }else{
                return Result.failure("no unit","还没有录入单元信息");
            }
        }catch (CtbException e){
            logger.error("glu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("glu sys-error. unitQO="+unitQO, e);
            return Result.failure();
        }
    }

    private Result getTts(TTBactchQO ttBactchQO) {
        //verify
        if (verify(ttBactchQO)) return Result.failure();

        Result<List<TTEntity>> ttRs = ttService.queryKpDetails(ttBactchQO);
        List<TTEntity> tts = ttRs.getData();
        if(null==tts || tts.size()==0){
            return Result.success();
        }
        String kpidStr = ttRs.getAttr(Keys.KPT_BATCH_ID);
        return Result.success(TTBuilder.build(Long.valueOf(kpidStr), ttBactchQO.getDl(), tts));
    }

    private boolean verify(TTBactchQO ttBactchQO) {
        if(null==ttBactchQO || StringUtil.isEmpty(ttBactchQO.getAtk())
                || null==ttBactchQO.getUn() || null==ttBactchQO.getDl()
                || null==ttBactchQO.getEok() || null==ttBactchQO.getRd()){
            return true;
        }
        if(StringUtil.isNotWideEmpty(ttBactchQO.getMkp())){
            Long mkp=Long.valueOf(ttBactchQO.getMkp());
            Assert.isTrue(mkp>0,"mkp error");
            ttBactchQO.setMaxKpId(mkp);
        }
        if(StringUtil.isNotWideEmpty(ttBactchQO.getMt())){
            Long mt=Long.valueOf(ttBactchQO.getMt());
            Assert.isTrue(mt>0,"mt error");
            ttBactchQO.setMaxTid(mt);
        }
        Result<BUsr> bUsrRS = usrService.getByAtk(ttBactchQO.getAtk());
        BUsr bUsr = bUsrRS.getData();
        if(null==bUsr){
            logger.error("[hacker attach!] not existed usr");
            return true;
        }
        ttBactchQO.setUid(bUsr.getId());
        return false;
    }


    public static void main(String[] args) throws Exception {
        String s = "[{\"idx\":\"A\",\"cont\":\"fdas\"},{\"idx\":\"B\",\"cont\":\"fdsa\"},{\"idx\":\"C\",\"cont\":\"123\"}]";
        List<EBatchOpt> list =  (List<EBatchOpt>) JacksonUtil.deserialize(s, new TypeReference<List<EBatchOpt>>(){/**/});
        System.out.println("===list="+ list.size());
    }

}
