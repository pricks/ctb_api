package com.bw.edu.ctb.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.TitleQO;
import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/mc")
public class MCController {
    private final static Logger logger = LoggerFactory.getLogger(MCController.class);

    /** get batch of titles */
    @RequestMapping("gb")
    public Result queryTitle(TitleQO titleQO, HttpServletRequest request){
        logger.error("query title. raddr=" + request.getRemoteAddr());
        Result titleDTOResult = new Result();
        List<CtbTitleEntity> titleEntityList = null;
        return Result.success(titleEntityList);
    }
}
