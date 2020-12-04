package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.enums.StatusEnum;
import com.bw.edu.ctb.common.qo.KpQO;
import com.bw.edu.ctb.common.qo.TTBactchQO;
import com.bw.edu.ctb.common.qo.TkrQO;
import com.bw.edu.ctb.dao.entity.KpEntity;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import com.bw.edu.ctb.dao.mapper.KpMapper;
import com.bw.edu.ctb.dao.mapper.TTMapper;
import com.bw.edu.ctb.dao.mapper.TkrMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.KpManager;
import com.bw.edu.ctb.manager.TkrManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TTService {
    @Autowired
    private TTMapper ttMapper;

    @Autowired
    private KpManager kpManager;
    @Autowired
    private TkrManager tkrManager;

    public Result<List<TTEntity>> queryKpDetails(TTBactchQO ttBactchQO){
        Long maxKpId = ttBactchQO.getMaxKpId();
        Long maxTid = ttBactchQO.getMaxTid();//maxTid可以为空
        if(null == maxKpId){
            Long un = ttBactchQO.getUn();
            Integer dl = ttBactchQO.getDl();
            if(null == un){
                throw new CtbException(CtbExceptionEnum.UNIT_IS_NULL);
            }
            if(null == dl){
                throw new CtbException(CtbExceptionEnum.DL_IS_NULL);
            }

            KpQO kpQO = new KpQO();
            kpQO.setUn(un);
            kpQO.setDl(dl);
            kpQO.setStatus(StatusEnum.PULISHED.getCode());
            kpQO.setNum(1);
            List<KpEntity> kps = kpManager.queryByUn(kpQO);
            if(null==kps || kps.size()==0){
                return Result.success();
            }
            maxKpId = kps.get(0).getId();
        }
        Integer eok = ttBactchQO.getEok();

        //查询当前kp下的tt，注：这些tt的id必须大于 maxTid
        List<TkrEntity> tkrs = new ArrayList<>();
        KpEntity k = kpManager.getByIdNotNull(maxKpId);
        searchKpDetails(k, maxTid, eok, tkrs, true, true);
        if(tkrs.size() == 0){
            return Result.success();
        }

        int size = tkrs.size();
        if(size > 10){
            tkrs = tkrs.subList(0, 9);
        }
        List<TTEntity> tts = new ArrayList<>(size);
        List<Long> tids = new ArrayList<>(size);
        for(TkrEntity te : tkrs){
            tids.add(te.getTid());
        }


        return Result.success(tkrs);
    }

    private void searchKpDetails(KpEntity k, Long maxTid, Integer eok, List<TkrEntity> tkrs, boolean searchParent, boolean searchForward){
        if(null == eok){
            throw new CtbException(CtbExceptionEnum.EOK_IS_NULL);
        }
        int size = tkrs.size();

        //1.查询当前kp下的tt，如果超过10个，就返回前面10个；否则进入第2步
        List<TkrEntity> kpDetails = tkrManager.queryTTs(k.getId(), eok);
        if(null != kpDetails && kpDetails.size() > 0){
            if(maxTid > 0){
                for(TkrEntity te : kpDetails){
                    if(te.getTid() > maxTid){
                        tkrs.add(te);
                    }
                }
            }else{
                tkrs.addAll(kpDetails);
            }
        }
        if(tkrs.size() >= 10){
            return;
        }

        //2.以当前kp节点为中心，查询其所有子节点的tt；如果还没有足够的tt，就查询当前kp的后续兄弟节点以及兄弟节点的子节点的所有tt
        List<KpEntity> subKps = kpManager.queryOrderdChildren(k.getId());
        if(null != subKps && subKps.size() > 0){
            KpEntity subk = subKps.get(0);
            searchKpDetails(subk, 0L, eok, tkrs, false, true);
            if(tkrs.size() >= 10){
                return;
            }
        }

        //3.如果没有children，则查询sibling kp，按照order正向排序
        //如果 searchForward==false，则既不搜索兄弟节点，也不搜索父节点
        if(!searchForward) return;
        List<KpEntity> sibkps = kpManager.querySiblings(k);
        if(null != sibkps && sibkps.size() > 0){
            for(KpEntity sibk : sibkps){
                searchKpDetails(sibk, 0L, eok, tkrs, false, false);
                if(tkrs.size() >= 10){
                    return;
                }
            }
        }

        //4.如果当前kp节点所在level的所有纵向节点下的tt数量还不够，就获取当前节点的父节点的后续第一个兄弟节点继续查询
        if(!searchParent) return;
        if(null == k.getPid()) return;
        KpEntity pk = kpManager.getByIdNotNull(k.getPid());
        List<KpEntity> siblings = kpManager.querySiblings(pk);
        if(null == siblings || siblings.size() == 0){
            return;
        }
        searchKpDetails(siblings.get(0), 0L, eok, tkrs, true,true);
    }
}
