package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.manager.ExRecManager;
import com.bw.edu.ctb.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExRecService {
    private final static Logger logger = LoggerFactory.getLogger(ExRecService.class);
    @Autowired
    private ExRecManager exRecManager;

    public Result<Long> selectLatestExr(Long uid){
        try {
            if(null==uid || uid<1L){
                return Result.success();
            }
            return Result.success(exRecManager.selectLatestExr(uid));
        } catch (CtbException e){
            logger.error("selectLatestExr failed. uid="+uid);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("selectLatestExr failed because system. uid="+uid, e);
            return Result.failure();
        }
    }
}
