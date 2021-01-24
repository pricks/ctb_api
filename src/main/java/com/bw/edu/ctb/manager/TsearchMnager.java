package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.constants.SystemConstants;
import com.bw.edu.ctb.dao.entity.KpEntity;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class TsearchMnager {
    @Autowired
    private KpManager kpManager;
    @Autowired
    private TkrManager tkrManager;

    /**
     * 搜索对外接口
     * @param kpid 从哪个节点开始搜索
     * @param maxTid 该节点下的最大tt id(tt统一按照id从小到大排序)
     * @param eok @see EokENUM
     * @param tkrs 外部调用时，传入被实例化的空队列，该队列用于记录最终搜索出来的结果
     * @return 最后的kp节点ID
     */
    public Long searchKpDetails(Long kpid, Long maxTid, Integer eok, int maxNum, List<TkrEntity> tkrs){
        if(maxNum <= 0){
            return null;
        }
        if(maxNum> SystemConstants.MAX_PAGE_DATA_NUM){
            maxNum = SystemConstants.MAX_PAGE_DATA_NUM;
        }
        Assert.notNull(kpid, "kpid is null");
        Assert.notNull(eok, "eok is null");
        Assert.notNull(tkrs, "tkrs is null");
        KpEntity k = kpManager.getByIdNotNull(kpid);
        if(null == maxTid) maxTid = 0L;
        Long maxKpId = searchKpDetails(k, maxTid, eok, maxNum, tkrs, true, true);
        if(tkrs.size()>maxNum){
            tkrs = tkrs.subList(0, maxNum-1);
        }
        return maxKpId;
    }

    /**
     * 搜索
     * @param k 从哪个节点开始搜索
     * @param maxTid 该节点下的最大tt id(tt统一按照id从小到大排序)
     * @param eok @see EokENUM
     * @param tkrs 外部调用时，传入被实例化的空队列，该队列用于记录最终搜索出来的结果
     * @param searchParent 第一次调用时传入true
     * @param searchForward 第一次调用时传入true
     * @return 最后的kp节点ID
     */
    private Long searchKpDetails(KpEntity k, Long maxTid, Integer eok, int maxNum, List<TkrEntity> tkrs, boolean searchParent, boolean searchForward){
        if(null == eok){
            throw new CtbException(CtbExceptionEnum.EOK_IS_NULL);
        }
        Long kpid = k.getId();

        //1.查询当前kp下的tt，如果超过 maxNum 个，就返回前面 maxNum 个；否则进入第2步
        List<TkrEntity> kpDetails = tkrManager.queryTTs(k.getId(), eok, maxNum);
        if(null != kpDetails && kpDetails.size() > 0){
            if(maxTid > 0){
                for(TkrEntity te : kpDetails){
                    if(te.getTid() > maxTid){
                        tkrs.add(te);
                    }

                    //todo 通过tkr的gc和exSttBycl的gc，计算出该用户的总共知识点
                }
            }else{
                tkrs.addAll(kpDetails);
            }
        }
        if(tkrs.size() >= maxNum){
            return kpid;
        }


        //2.以当前kp节点为中心，查询其所有子节点的tt；如果还没有足够的tt，就查询当前kp的后续兄弟节点以及兄弟节点的子节点的所有tt
        List<KpEntity> subKps = kpManager.queryOrderdChildren(k.getId());
        if(null != subKps && subKps.size() > 0){
            KpEntity subk = subKps.get(0);
            kpid = searchKpDetails(subk, 0L, eok, maxNum, tkrs, false, true);
            if(tkrs.size() >= maxNum){
                return kpid;
            }
        }

        //3.如果没有children，则查询sibling kp，按照order正向排序
        //如果 searchForward==false，则既不搜索兄弟节点，也不搜索父节点
        if(!searchForward) return kpid;
        List<KpEntity> sibkps = kpManager.querySiblings(k);
        if(null != sibkps && sibkps.size() > 0){
            for(KpEntity sibk : sibkps){
                kpid = searchKpDetails(sibk, 0L, eok, maxNum, tkrs, false, false);
                if(tkrs.size() >= maxNum){
                    return kpid;
                }
            }
        }

        //4.如果当前kp节点所在level的所有纵向节点下的tt数量还不够，就获取当前节点的父节点的后续第一个兄弟节点继续查询
        if(!searchParent) return kpid;
        if(null == k.getPid()) return kpid;
        KpEntity pk = kpManager.getByIdNotNull(k.getPid());
        List<KpEntity> siblings = kpManager.querySiblings(pk);
        if(null == siblings || siblings.size() == 0){
            return kpid;
        }
        return searchKpDetails(siblings.get(0), 0L, eok, maxNum, tkrs, true,true);
    }
}
