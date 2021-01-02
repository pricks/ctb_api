package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.dao.entity.ExRecEntity;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import com.bw.edu.ctb.dao.entity.SGEntity;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.dto.SsDTO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.manager.KptBatchManager;
import com.bw.edu.ctb.service.ExRecService;
import com.bw.edu.ctb.service.KptBatchService;
import com.bw.edu.ctb.service.SGService;
import com.bw.edu.ctb.service.usr.UsrService;
import com.bw.edu.ctb.common.util.CollectionUtil;
import com.bw.edu.ctb.common.util.StringUtil;
import com.bw.edu.ctb.web.vo.ExRecVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mr")
public class MRCController {
    private final static Logger logger = LoggerFactory.getLogger(MRCController.class);
    @Autowired
    private UsrService usrService;
    @Autowired
    private ExRecService exRecService;
    @Autowired
    private SGService sgService;
    @Autowired
    private KptBatchService kptBatchService;

    /** commit rv_rec */
    @PostMapping("/c")
    public Result<List<SsDTO>> tijiao_rv_record(ExRecVO exRecVO, HttpServletRequest request){
        try{
            //verify
            if(null==exRecVO || StringUtil.isEmpty(exRecVO.getAtk())
                    || StringUtil.isEmpty(exRecVO.getTts())
                    || null==exRecVO.getKptBatch()){
                return Result.failure();
            }
            Result<BUsr> bUsrRS = usrService.getByAtk(exRecVO.getAtk());
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }

            Result<KptBatchEntity> kptBatchEntityResult = kptBatchService.selectById(exRecVO.getKptBatch());
            if(!kptBatchEntityResult.isSuccess() || null==kptBatchEntityResult.getData()){
                promoteException(kptBatchEntityResult.getCode(), kptBatchEntityResult.getMessage());
            }

            //create ex_rec
            Long eid = createExRec(exRecVO, bUsr.getId(), kptBatchEntityResult.getData());
            return Result.success();
        }catch (CtbException e){
            logger.error("fs biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("fs sys-error", e);
            return Result.failure();
        }
    }

    private Long createExRec(ExRecVO exRecVO, Long uid, KptBatchEntity k){
        ExRecEntity ee = new ExRecEntity();
        ee.setUn(exRecVO.getUn());
        ee.setDl(exRecVO.getDl());
        ee.setUid(uid);
        ee.setRd(exRecVO.getRd());
        ee.setBatchId(exRecVO.getKptBatch());
        ee.setTts(exRecVO.getTts());
        ee.setKns(exRecVO.getKns());
        ee.setwKns(exRecVO.getWkns());
        ee.setwTts(exRecVO.getWtts());
        ee.setScore(exRecVO.getScore());
        ee.setCkc(exRecVO.getCkc());
        ee.setMaxk(k.getMaxKpid());//发给客户端的tt batch就是按照kn排序的
        ee.setMaxt(k.getMaxTid());
        Result<Long> saveRS = exRecService.create(ee);
        if(!saveRS.isSuccess() || null==saveRS.getData()){
            promoteException(saveRS.getCode(), saveRS.getMessage());
        }
        return saveRS.getData();
    }
}
