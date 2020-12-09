package com.bw.edu.ctb.web;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.dao.entity.ExSttByclEntity;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.bw.edu.ctb.domain.SttClDO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.web.controller.MCController;
import com.bw.edu.ctb.web.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/l")
public class LgController {
    private final static Logger logger = LoggerFactory.getLogger(LgController.class);
    /** login */
//    @RequestMapping("g")
    @PostMapping("/g")
    public Result gu(UserVO map, HttpServletRequest request, HttpSession session){
        try{
            logger.error("g. r=" + request.getRemoteAddr() + ", model="+map);

            return Result.success();
        }catch (CtbException e){
            logger.error("gu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
//            logger.error("gu sys-error. unitQO="+unitQO, e);
            return Result.failure();
        }
    }
}
