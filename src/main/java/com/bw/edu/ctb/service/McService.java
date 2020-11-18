package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.PPracBatchQO;
import com.bw.edu.ctb.dao.entity.ExbatchEntity;
import com.bw.edu.ctb.dao.entity.PPracBatchEntity;
import com.bw.edu.ctb.dao.mapper.PPracBatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class McService {
    @Autowired
//    private PPracBatchMapper pPracBatchMapper;

    public Result<Void> fetchBatch(){
        //1.try to search the batch
        List<ExbatchEntity> exbatchEntityList = null;//query batch

        return null;
    }

    public Result<PPracBatchEntity> fetchTitleBatch(PPracBatchQO pPracBatchQO){
        Result<PPracBatchEntity> result = new Result();
        result.setSuccess(true);

        //query the latest practice batch
//        PPracBatchEntity pPracBatchEntity = pPracBatchMapper.selectLatest(pPracBatchQO);
//        if(null == pPracBatchEntity){
//            return result;
//        }

        return result;
    }
}
