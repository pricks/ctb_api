package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.ExSttQO;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.dao.entity.ExSttEntity;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.bw.edu.ctb.dao.mapper.ExSttMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExSttService {
    @Autowired
    private ExSttMapper exSttMapper;

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
