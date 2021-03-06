package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.enums.EokEnum;
import com.bw.edu.ctb.common.enums.StatusEnum;
import com.bw.edu.ctb.common.enums.subjects.DGRel;
import com.bw.edu.ctb.common.enums.subjects.GradeEnum;
import com.bw.edu.ctb.common.enums.subjects.TTypeEnum;
import com.bw.edu.ctb.common.qo.KpQO;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.common.util.CollectionUtil;
import com.bw.edu.ctb.common.util.JacksonUtil;
import com.bw.edu.ctb.common.util.StringUtil;
import com.bw.edu.ctb.dao.entity.*;
import com.bw.edu.ctb.dto.SsDTO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.service.KpService;
import com.bw.edu.ctb.service.SGService;
import com.bw.edu.ctb.service.TTService;
import com.bw.edu.ctb.service.UnitService;
import com.bw.edu.ctb.web.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

/** 基础数据对外请求控制类 */
@RestController
@RequestMapping("/b")
public class SubjectController {
    private final static Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SGService sgService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private KpService kpService;
    @Autowired
    private TTService ttService;

    @GetMapping("/gd")
    @CrossOrigin
    public Result query__grade(HttpServletRequest request){
        try{
            logger.error("query title. raddr=" + request.getRemoteAddr());
            String dgStr = request.getParameter("dg");
            if(StringUtil.isEmpty(dgStr)){
                return Result.failure(CtbExceptionEnum.PARAM_NULL.getCode(), CtbExceptionEnum.PARAM_NULL.getDesc());
            }
            Integer dg = Integer.valueOf(dgStr);
            List<GradeEnum> gradeEnums = DGRel.getGrade(dg);
            if(CollectionUtil.isEmpty(gradeEnums)){
                return Result.success();
            }
            List<GradeVO> gradeVOS = new ArrayList<>(gradeEnums.size());
            for(GradeEnum ge : gradeEnums){
                GradeVO gv = new GradeVO();
                gv.setName(ge.getName());
                gv.setV(ge.getCode());
                gradeVOS.add(gv);
            }
            return Result.success(gradeVOS);
        }catch (CtbException e){
            logger.error("gd biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("gd sys-error", e);
            return Result.failure();
        }
    }

    @GetMapping("/s")
    @CrossOrigin
    public Result query__subjects(HttpServletRequest request){
        try{
            logger.error("query title. raddr=" + request.getRemoteAddr());
            String dgStr = request.getParameter("dg");
            String gdStr = request.getParameter("gd");
            if(StringUtil.isEmpty(dgStr) || StringUtil.isEmpty(gdStr)){
                return Result.failure(CtbExceptionEnum.PARAM_NULL.getCode(), CtbExceptionEnum.PARAM_NULL.getDesc());
            }
            Integer dg = Integer.valueOf(dgStr);
            Integer gd = Integer.valueOf(gdStr);
            Result<List<SGEntity>> sgRS = sgService.select(dg, gd);
            if(!sgRS.isSuccess()){
                //获取默认的gd & class
                return Result.failure();
            }
            if(CollectionUtil.isEmpty(sgRS.getData())){
                //获取默认的gd & class
                logger.error("[fatal error] query no subjects. dg="+dg+", gd="+gd);
                return Result.success();
            }
            List<SsDTO> ssDTOList = new ArrayList<>(sgRS.getData().size());
            for(SGEntity sg : sgRS.getData()){
                SsDTO ss = new SsDTO();
                ss.setSc(sg.getSc());
                ss.setSn(sg.getSn());
                ssDTOList.add(ss);
            }
            return Result.success(ssDTOList);
        }catch (CtbException e){
            logger.error("b/s biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("b/s sys-error", e);
            return Result.failure();
        }
    }

    @GetMapping("/u")
    @CrossOrigin
    public Result query__units(HttpServletRequest request){
        try{
            logger.error("query title. raddr=" + request.getRemoteAddr());
            String dgStr = request.getParameter("dg");
            String gdStr = request.getParameter("gd");
            String clStr = request.getParameter("cl");
            if(StringUtil.isEmpty(dgStr) || StringUtil.isEmpty(gdStr) || StringUtil.isEmpty(clStr)){
                return Result.failure(CtbExceptionEnum.PARAM_NULL.getCode(), CtbExceptionEnum.PARAM_NULL.getDesc());
            }
            Integer dg = Integer.valueOf(dgStr);
            Integer gd = Integer.valueOf(gdStr);
            Integer cl = Integer.valueOf(clStr);
            UnitQO uq = new UnitQO();
            uq.setDg(dg);
            uq.setGd(gd);
            uq.setCl(cl);
            Result<List<UnitEntity>> unRS = unitService.queryByCl(uq);
            if(!unRS.isSuccess()){ return Result.failure(); }
            if(CollectionUtil.isEmpty(unRS.getData())){
                //获取默认的gd & class
                logger.error("[fatal error] query no units. uq="+uq);
                return Result.success();
            }
            List<UnitVO> uvs = new ArrayList<>(unRS.getData().size());
            for(UnitEntity ue : unRS.getData()){
                UnitVO uv = new UnitVO();
                uv.setUc(ue.getCode());
                uv.setUn(ue.getName());
                uvs.add(uv);
            }
            return Result.success(uvs);
        }catch (CtbException e){
            logger.error("b/s biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("b/s sys-error", e);
            return Result.failure();
        }
    }

    @GetMapping("/k")
    @CrossOrigin
    public Result query__kps(HttpServletRequest request){
        try{
            logger.error("query title. raddr=" + request.getRemoteAddr());
            String unStr = request.getParameter("un");
            String dlStr = request.getParameter("dl");
            if(StringUtil.isEmpty(unStr) || StringUtil.isEmpty(dlStr)){
                return Result.failure(CtbExceptionEnum.PARAM_NULL.getCode(), CtbExceptionEnum.PARAM_NULL.getDesc());
            }
            Long un = Long.valueOf(unStr);
            Integer dl = Integer.valueOf(dlStr);
            KpQO kpQO = new KpQO();
            kpQO.setUn(un);
            kpQO.setDl(dl);
            kpQO.setStatus(StatusEnum.PULISHED.getCode());
            Result<List<KpEntity>> kpRS = kpService.query(kpQO);
            if(!kpRS.isSuccess()){ return Result.failure(); }
            if(CollectionUtil.isEmpty(kpRS.getData())){
                //获取默认的gd & class
                logger.error("[fatal error] query no kps. kq="+kpQO);
                return Result.success();
            }

            List<KpVO> kvs = new ArrayList<>();
            Map<Long, List<KpEntity>> map = new HashMap<>();
            for(KpEntity kp : kpRS.getData()){
                if(null==kp.getPid() && kp.getLevel().equals(1)){
                    // 遍历第1层级的kp
                    KpVO kv = new KpVO();
                    kv.setValue(kp.getId());
                    kv.setLabel(kp.getPoint());
                    kv.setKorder(kp.getKorder());
                    kv.setLevel(kp.getLevel());
                    kv.setP(kp.getPid());
                    kvs.add(kv);
                } else {
                    // 构建以pid为key的hash表
                    if(!map.containsKey(kp.getPid())){
                        List<KpEntity> kps = new ArrayList<>();
                        kps.add(kp);
                        map.put(kp.getPid(), kps);
                    }else{
                        List<KpEntity> kps = map.get(kp.getPid());
                        kps.add(kp);
                    }
                }
            }
            buildKV(kvs, map);
            return Result.success(kvs);
        }catch (CtbException e){
            logger.error("b/k biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("b/k sys-error", e);
            return Result.failure();
        }
    }
    private void buildKV(List<KpVO> kvs, Map<Long, List<KpEntity>> map){
        for(KpVO kv : kvs){
            Long pid = kv.getValue();
            if(map.containsKey(pid)){
                List<KpVO> tempKvs = new ArrayList<>();
                for(KpEntity kp : map.get(pid)){
                    KpVO tempKv = new KpVO();
                    tempKv.setValue(kp.getId());
                    tempKv.setLabel(kp.getPoint());
                    tempKv.setKorder(kp.getKorder());
                    tempKv.setLevel(kp.getLevel());
                    tempKv.setP(kp.getPid());
                    tempKvs.add(tempKv);
                }
                kv.setChildren(tempKvs);

                buildKV(tempKvs, map);
            }
        }
    }

    @PostMapping("/w")
    @CrossOrigin
    public Result __save__t(@RequestBody TtVO ttVO){
        try {
            checkTitle(ttVO);
            TTEntity t = convert(ttVO);
            Result<Void> ttRs = ttService.saveTT(t, ttVO.getKp().get(ttVO.getKp().size()-1));
            if(!ttRs.isSuccess()){ return Result.failure(); }
            return Result.success();
        } catch (CtbException e){
            logger.error("b/w biz-error", e);
            return Result.failure(e);
        } catch (Exception e){
            logger.error("b/w sys-error", e);
            return Result.failure();
        }
    }
    private TTEntity convert(TtVO ttVO) throws Exception{
        TTEntity t = new TTEntity();
        t.setUn(ttVO.getUn());
        t.setDl(ttVO.getDl());
        t.setTc(ttVO.getContent());
        t.setEok(EokEnum.KP_DETAIL.getCode());
        t.setType(ttVO.getType());
        t.setAid(1L);
        t.setOi(t.getType().equals(TTypeEnum.DANXUAN.getCode())?false:true);
        t.setTs(StatusEnum.PULISHED.getCode());
        if(TTypeEnum.DANXUAN.getCode().equals(ttVO.getType())){
            Integer ca = ttVO.getOps();
            if(ca.equals(1)) t.setTca("A");
            if(ca.equals(2)) t.setTca("B");
            if(ca.equals(3)) t.setTca("C");
            if(ca.equals(4)) t.setTca("D");

            List<Map<String, String>> maps = new ArrayList<>(4);
            if(StringUtil.isNotEmpty(ttVO.getaA())){
                Map<String, String> ops = new HashMap<>();
                ops.put("idx", "A");
                ops.put("cont", ttVO.getaA());
                maps.add(ops);
            }
            if(StringUtil.isNotEmpty(ttVO.getaB())){
                Map<String, String> ops = new HashMap<>();
                ops.put("idx", "B");
                ops.put("cont", ttVO.getaB());
                maps.add(ops);
            }
            if(StringUtil.isNotEmpty(ttVO.getaC())){
                Map<String, String> ops = new HashMap<>();
                ops.put("idx", "C");
                ops.put("cont", ttVO.getaC());
                maps.add(ops);
            }
            if(StringUtil.isNotEmpty(ttVO.getaD())){
                Map<String, String> ops = new HashMap<>();
                ops.put("idx", "D");
                ops.put("cont", ttVO.getaD());
                maps.add(ops);
            }
            t.setOts(JacksonUtil.serialize(maps));
        }
        if(TTypeEnum.WENDA.getCode().equals(ttVO.getType())
                || TTypeEnum.TIANKONG.getCode().equals(ttVO.getType())
                || TTypeEnum.YINGYONG.getCode().equals(ttVO.getType())){
            t.setTat(ttVO.getAnswer());
        }
        return t;
    }

    private String cutContent(String cont){
        if(null == cont) return null;
        cont = cont.trim();
        if(cont.length() <= 50) return cont;
        return cont.substring(0, 50);
    }
    private String extractCover(String cont){
        if(null == cont) return null;
        cont = cont.substring(cont.indexOf("<img")+4);
        cont = cont.trim();
        if(cont.startsWith("src")){
            cont = cont.substring(cont.indexOf("src=\"")+5);
            cont = cont.substring(0, cont.indexOf("\""));
            return cont;
        }
        return null;
    }
    private void checkTitle(TtVO ttVO){
        if(null == ttVO){
            promoteException(CtbExceptionEnum.OBJECT_NULL, "ttVO="+ttVO);
        }
        if(null == ttVO.getDg() || null==ttVO.getGd()
                || null==ttVO.getCl() || null==ttVO.getUn()
                || null==ttVO.getDl() || CollectionUtil.isEmpty(ttVO.getKp()) || null==ttVO.getType()){
            promoteException(CtbExceptionEnum.PARAM_NOT_RULED, "ttVO="+ttVO);
        }
        if(null == ttVO.getContent() || "".equals(ttVO.getContent().trim())){
            promoteException(CtbExceptionEnum.PARAM_NOT_RULED, "ttVO="+ttVO);
        }
        if(TTypeEnum.DANXUAN.getCode().equals(ttVO.getType())){
            if(null==ttVO.getOps()){
                promoteException(CtbExceptionEnum.PARAM_NOT_RULED, "ttVO="+ttVO);
            }
        }
        if(TTypeEnum.WENDA.getCode().equals(ttVO.getType())
            || TTypeEnum.TIANKONG.getCode().equals(ttVO.getType())
            || TTypeEnum.YINGYONG.getCode().equals(ttVO.getType())){
            if(StringUtil.isEmpty(ttVO.getAnswer())){
                promoteException(CtbExceptionEnum.PARAM_NOT_RULED, "ttVO="+ttVO);
            }
        }
    }
}
