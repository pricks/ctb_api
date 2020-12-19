package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.Keys;
import com.bw.edu.ctb.common.qo.TTBactchQO;
import com.bw.edu.ctb.common.qo.TitleQO;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.dao.entity.*;
import com.bw.edu.ctb.domain.EBatch;
import com.bw.edu.ctb.domain.EBatchTT;
import com.bw.edu.ctb.domain.SttClDO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.service.ExSttService;
import com.bw.edu.ctb.service.TTService;
import com.bw.edu.ctb.service.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mc")
public class MCController {
    private final static Logger logger = LoggerFactory.getLogger(MCController.class);

    @Autowired
    private UnitService unitService;
    @Autowired
    private ExSttService exSttService;
    @Autowired
    private TTService ttService;

    /** get user's unit ex_stt */
    @RequestMapping("gu")
    public Result gu(UnitQO unitQO, HttpServletRequest request, HttpSession session){
        try{
            logger.error("gu. r=" + request.getRemoteAddr() + ", uid="+session.getAttribute("uid"));

            Result<List<UnitEntity>> unitRs = unitService.queryByCl(unitQO);
            Result<ExSttByclEntity> exSttByclEntityResult = exSttService.queryExSttBycl((Long) session.getAttribute("uid"), unitQO.getCl());
            if(null == exSttByclEntityResult.getData()){
                //todo 写表，写入一条空记录
                return Result.success(SttClDO.buildEmpty(unitRs.getData()));
            }else{
                if(exSttByclEntityResult.isSuccess()){
                    return Result.success(exSttByclEntityResult.getData().getStt());
                }else{
                    return Result.success(SttClDO.buildEmpty(unitRs.getData()));
                }
            }
        }catch (CtbException e){
            logger.error("gu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("gu sys-error. unitQO="+unitQO, e);
            return Result.failure();
        }
    }

    /** get user's tts batch */
    @RequestMapping("gt")
    public Result gt(TTBactchQO ttBactchQO, HttpServletRequest request, HttpSession session){
        try{
            logger.error("gt. r=" + request.getRemoteAddr() + ", uid="+session.getAttribute("uid"));
            Result<List<TTEntity>> ttRs = ttService.queryKpDetails(ttBactchQO);
            List<TTEntity> tts = ttRs.getData();
            if(null==tts || tts.size()==0){
                return Result.success();
            }
            String kpidStr = ttRs.getAttr(Keys.KPT_BATCH_ID);
            return Result.success(build(Long.valueOf(kpidStr), ttBactchQO.getDl(), tts));
        }catch (CtbException e){
            logger.error("gu biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("gu sys-error. ttBactchQO="+ttBactchQO, e);
            return Result.failure();
        }
    }

    private EBatch build(Long kpId, Integer dl, List<TTEntity> tts){
        EBatch eb = new EBatch();
        eb.setEb_id(kpId);
        eb.setKp_dl(dl);

        List<EBatchTT> eBatchTTList = new ArrayList<>();
        int size = tts.size();
        eb.setTt_count(size);
        eb.setTt_g_merge_count(size);//todo 这里后面要改成真正的值
        for(int k=0; k<size; k++){
            TTEntity tt = tts.get(k);
            EBatchTT et = new EBatchTT();
            et.setTt_g(false);//todo 这里要真正进行判断
            et.setT_idx(k);
            et.setTid(tt.getId());
            et.setTt_ct(tt.getTc());
            et.setTt_offline(tt.getOi());
            et.setTt_type(tt.gettType());
            et.setT_ops(null);//todo
            et.setTt_answer(tt.getTca());
            et.setT_g_count(0);//todo
            eBatchTTList.add(et);
        }
        eb.setTt_list(eBatchTTList);
        return eb;
    }

    /** get batch of titles */
    @RequestMapping("gb")
    public Result queryTitle(TitleQO titleQO, HttpServletRequest request){
        logger.error("query title. raddr=" + request.getRemoteAddr());
        Result titleDTOResult = new Result();
        List<CtbTitleEntity> titleEntityList = null;
        return Result.success(titleEntityList);
    }
}
