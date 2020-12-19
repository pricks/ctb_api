package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.Keys;
import com.bw.edu.ctb.common.qo.TTBactchQO;
import com.bw.edu.ctb.common.qo.TitleQO;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.common.util.JacksonUtil;
import com.bw.edu.ctb.common.util.StringUtil;
import com.bw.edu.ctb.dao.entity.*;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.domain.*;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.service.ExSttService;
import com.bw.edu.ctb.service.TTService;
import com.bw.edu.ctb.service.UnitService;
import com.bw.edu.ctb.service.usr.UsrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    /** get user's unit ex_stt */
    @RequestMapping("gu")
    public Result gu(UnitQO unitQO, HttpServletRequest request){
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

            Result<List<UnitEntity>> unitRs = unitService.queryByCl(unitQO);
            Result<ExSttByclEntity> exSttByclEntityResult = exSttService.queryExSttBycl(bUsr.getId(), unitQO.getCl());
            if(null == exSttByclEntityResult.getData()){
                //写表，写入一条空记录
                SttClDO stt = SttClDO.buildEmpty(unitRs.getData(), UnitDO.build(unitQO));
                String sttStr = JacksonUtil.serialize(stt);
                ExSttByclEntity ebe = new ExSttByclEntity();
                ebe.setUid(1L);
                ebe.setDg(unitQO.getDg());
                ebe.setGd(unitQO.getGd());
                ebe.setCl(unitQO.getCl());
                ebe.setStt(sttStr);
                exSttService.updateStt(ebe);

                return Result.success(sttStr);
            }else{
                if(exSttByclEntityResult.isSuccess()){
                    return Result.success(exSttByclEntityResult.getData().getStt());
                }else{
                    SttClDO stt = SttClDO.buildEmpty(unitRs.getData(), UnitDO.build(unitQO));
                    String sttStr = JacksonUtil.serialize(stt);
                    return Result.success(sttStr);
                }
            }
        }catch (CtbException e){
            logger.error("gu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("gu sys-error. unitQO="+unitQO, e);
            return Result.failure();
        }
    }

    /** get user's tts batch */
    @RequestMapping("gt")
    public Result gt(TTBactchQO ttBactchQO, HttpServletRequest request){
        try{
            logger.error("gt. r=" + request.getRemoteAddr());

            //verify
            if(null==ttBactchQO || StringUtil.isEmpty(ttBactchQO.getAtk())
                    || null==ttBactchQO.getUn() || null==ttBactchQO.getDl()
                    || null==ttBactchQO.getEok()){
                return Result.failure();
            }
            Result<BUsr> bUsrRS = usrService.getByAtk(ttBactchQO.getAtk());
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }
            ttBactchQO.setUid(bUsr.getId());

            Result<List<TTEntity>> ttRs = ttService.queryKpDetails(ttBactchQO);
            List<TTEntity> tts = ttRs.getData();
            if(null==tts || tts.size()==0){
                return Result.success();
            }
            String kpidStr = ttRs.getAttr(Keys.KPT_BATCH_ID);
            return Result.success(build(Long.valueOf(kpidStr), ttBactchQO.getDl(), tts));
        }catch (CtbException e){
            logger.error("gu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("gu sys-error. ttBactchQO="+ttBactchQO, e);
            return Result.failure();
        }
    }

    private EBatch build(Long kpId, Integer dl, List<TTEntity> tts){
        EBatch eb = new EBatch();
        eb.setEb_id(kpId);
        eb.setKp_dl(dl);

        List<EBatchTT> eBatchTTList = new ArrayList<>();
        int size = tts.size();
        eb.setTt_count(size);
        eb.setTt_g_merge_count(size);//todo 这里后面要改成真正的值
        for(int k=0; k<size; k++){
            TTEntity tt = tts.get(k);
            EBatchTT et = new EBatchTT();
            et.setTt_g(false);//todo 这里要真正进行判断
            et.setT_idx(k);
            et.setTid(tt.getId());
            et.setTt_ct(tt.getTc());
            et.setTt_offline(tt.getOi());
            et.setTt_type(tt.gettType());
            et.setT_ops(null);//todo
            et.setTt_answer(tt.getTca());
            et.setT_g_count(0);//todo
            eBatchTTList.add(et);
        }
        eb.setTt_list(eBatchTTList);
        return eb;
    }

    /** get batch of titles */
    @RequestMapping("gb")
    public Result queryTitle(TitleQO titleQO, HttpServletRequest request){
        logger.error("query title. raddr=" + request.getRemoteAddr());
        Result titleDTOResult = new Result();
        List<CtbTitleEntity> titleEntityList = null;
        return Result.success(titleEntityList);
    }
}
