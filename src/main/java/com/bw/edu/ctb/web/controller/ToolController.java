package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.Keys;
import com.bw.edu.ctb.common.qo.TTBactchQO;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.notify.PAC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/t")
public class ToolController {
    private final static Logger logger = LoggerFactory.getLogger(ToolController.class);
    @Autowired
    private PAC pac;

    /** produce ex_rec.  /t/per?eid=1 */
    @RequestMapping("per")
    public Result produce_ex_rec(HttpServletRequest request){
        try{
            logger.error("t/per. r=" + request.getRemoteAddr());
            Long erid = Long.valueOf(request.getParameter("eid"));
            pac.produce(erid);
            return Result.success();
        }catch (CtbException e){
            logger.error("per biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("per sys-error");
            return Result.failure();
        }
    }

    @RequestMapping("ps")
    public Result get_pac_size(HttpServletRequest request){
        try{
            return Result.success(pac.getCapacity());
        }catch (CtbException e){
            logger.error("per biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("per sys-error");
            return Result.failure();
        }
    }
}
