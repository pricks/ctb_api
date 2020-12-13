package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.dao.entity.SGEntity;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.manager.SGManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SGService {
    private final static Logger logger = LoggerFactory.getLogger(SGService.class);
    @Autowired
    private SGManager sgManager;

    public Result<Void> create(SGEntity sg){
        try {
            sgManager.save(sg);
            return Result.success();
        }catch (Exception e){
            logger.error("selectLatestExr failed because system. sg="+sg);
            return Result.failure();
        }
    }

    public Result<List<SGEntity>> select(int dg, int gd){
        try {
            return Result.success(sgManager.select(dg, gd));
        } catch (CtbException e){
            logger.error("selectLatestExr failed. dg="+dg+", gd="+gd);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("selectLatestExr failed because system. dg="+dg+", gd="+gd);
            return Result.failure();
        }
    }
}
