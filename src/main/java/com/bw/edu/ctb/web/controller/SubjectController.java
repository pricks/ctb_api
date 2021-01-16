package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.enums.subjects.DGRel;
import com.bw.edu.ctb.common.enums.subjects.GradeEnum;
import com.bw.edu.ctb.common.qo.TitleQO;
import com.bw.edu.ctb.common.util.CollectionUtil;
import com.bw.edu.ctb.common.util.StringUtil;
import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.web.vo.GradeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 基础数据对外请求控制类 */
@RestController
@RequestMapping("/b")
public class SubjectController {
    private final static Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @GetMapping("/gd")
    @CrossOrigin
    public Result queryGrade(HttpServletRequest request){
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
}
