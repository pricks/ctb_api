package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.ExSttByclQO;
import com.bw.edu.ctb.common.qo.ExSttQO;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.dao.entity.ExSttByclEntity;
import com.bw.edu.ctb.dao.entity.ExSttEntity;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.bw.edu.ctb.dao.mapper.ExSttByclMapper;
import com.bw.edu.ctb.dao.mapper.ExSttMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.ExSttByclManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ExSttService {
    private final static Logger logger = LoggerFactory.getLogger(ExSttService.class);
    @Autowired
    private ExSttMapper exSttMapper;
    @Autowired
    private ExSttByclManager exSttByclManager;

    public Result<Void> create(ExSttByclEntity ebe){
        try{
            exSttByclManager.save(ebe);
            return Result.success();
        }catch (CtbException e){
            logger.error("saveStt failed. ebe="+ebe);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("saveStt failed because system. ebe="+ebe, e);
            return Result.failure();
        }
    }

    public Result<Void> updateStt(ExSttByclEntity ebe){
        try{
            exSttByclManager.update(ebe);
            return Result.success();
        }catch (CtbException e){
            logger.error("updateStt failed. ebe="+ebe);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("updateStt failed because system. ebe="+ebe, e);
            return Result.failure();
        }
    }

    /**
     * 查询指定用户在当前课程下的ex_stt_by_class todo 走缓存
     * @return
     */
    public Result<ExSttByclEntity> queryExSttBycl(ExSttByclQO qo){
        try{
            if(null==qo || null==qo.getDg() || null==qo.getGd() || null==qo.getCl() || null==qo.getUid()){
                throw new CtbException(CtbExceptionEnum.PARAM_NULL, "qo="+qo);
            }
            List<ExSttByclEntity> exSttEntityList = exSttByclManager.select(qo);
            if(null==exSttEntityList || exSttEntityList.size()==0){
                return Result.success();
            }
            if(exSttEntityList.size() > 1){
                throw new CtbException(CtbExceptionEnum.EX_STT_BYCL_TOO_MANY, "qo="+qo);
            }
            return Result.success(exSttEntityList.get(0));
        }catch (CtbException e){
            logger.error("biz-err. qo="+qo);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("sys-err. qo="+qo, e);
            return Result.failure();
        }
    }

    /**
     * 查询指定用户在当前课程下的所有ex_stt todo 走缓存
     * @param uq
     * @param uid
     * @return
     */
    public Result<ExSttEntity> queryExStt(UnitQO uq, Long uid){
        ExSttQO exSttQO = new ExSttQO();
        exSttQO.setUid(uid);
        exSttQO.setUn(uq.getCode());
        exSttQO.setStatus(1);
        List<ExSttEntity> exSttEntityList = exSttMapper.selectProfile(exSttQO);
        if(exSttEntityList == null || exSttEntityList.size() == 0){
            return Result.success();
        }//如果查询不到，说明用户还没有测试过，正常返回
        return Result.success(exSttEntityList);
    }
}
