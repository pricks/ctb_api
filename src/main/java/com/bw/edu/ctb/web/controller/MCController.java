package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.TTBactchQO;
import com.bw.edu.ctb.common.qo.TitleQO;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import com.bw.edu.ctb.dao.entity.ExSttByclEntity;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.bw.edu.ctb.domain.SttClDO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.service.ExSttService;
import com.bw.edu.ctb.service.TTService;
import com.bw.edu.ctb.service.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    /** get user's unit ex_stt */
    @RequestMapping("gu")
    public Result gu(UnitQO unitQO, HttpServletRequest request, HttpSession session){
        try{
            logger.error("gu. r=" + request.getRemoteAddr() + ", uid="+session.getAttribute("uid"));

            Result<List<UnitEntity>> unitRs = unitService.queryByCl(unitQO);
            Result<ExSttByclEntity> exSttByclEntityResult = exSttService.queryExSttBycl((Long) session.getAttribute("uid"), unitQO.getCl());
            if(null == exSttByclEntityResult.getData()){
                return Result.success(SttClDO.buildEmpty(unitRs.getData()));
            }else{
                if(exSttByclEntityResult.isSuccess()){
                    return Result.success(exSttByclEntityResult.getData().getStt());
                }else{
                    return Result.success(SttClDO.buildEmpty(unitRs.getData()));
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
    public Result gt(TTBactchQO ttBactchQO, HttpServletRequest request, HttpSession session){
        try{
            logger.error("gt. r=" + request.getRemoteAddr() + ", uid="+session.getAttribute("uid"));
            Result<List<TTEntity>> ttRs = ttService.queryKpDetails(ttBactchQO);
            return ttRs;
        }catch (CtbException e){
            logger.error("gu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("gu sys-error. ttBactchQO="+ttBactchQO, e);
            return Result.failure();
        }
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
