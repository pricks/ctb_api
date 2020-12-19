package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.dao.entity.ExRecEntity;
import com.bw.edu.ctb.dao.entity.SGEntity;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.dto.SsDTO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.service.ExRecService;
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

    /** commit rv_rec */
    @PostMapping("/c")
    public Result<List<SsDTO>> tijiao_rv_record(ExRecVO exRecVO, HttpServletRequest request){
        try{
            //verify
            if(null==exRecVO || StringUtil.isEmpty(exRecVO.getAtk()) || StringUtil.isEmpty(exRecVO.getTts())){
                return Result.failure();
            }
            Result<BUsr> bUsrRS = usrService.getByAtk(exRecVO.getAtk());
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }

            //create ex_rec
            Long eid = createExRec(exRecVO, bUsr.getId());

            //produce


            Integer dg = Integer.valueOf(request.getParameter("dg"));
            Integer gd = Integer.valueOf(request.getParameter("gd"));
            Result<List<SGEntity>> sgRS = sgService.select(dg, gd);
            if(!sgRS.isSuccess() || CollectionUtil.isEmpty(sgRS.getData())){
                //获取默认的gd & class
                logger.error("[fatal error] query no subjects. dg="+dg+", gd="+gd);
                return Result.failure();
            }
            List<SsDTO> ssDTOList = new ArrayList<>(sgRS.getData().size());
            for(SGEntity sg : sgRS.getData()){
                SsDTO ss = new SsDTO();
                ss.setSc(sg.getSc());
                ss.setSn(ss.getSn());
                ssDTOList.add(ss);
            }
            return Result.success(ssDTOList);
        }catch (CtbException e){
            logger.error("fs biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("fs sys-error", e);
            return Result.failure();
        }
    }

    private Long createExRec(ExRecVO exRecVO, Long uid){
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
        ee.setMaxk(exRecVO.getMaxk());//发给客户端的tt batch就是按照kn排序的
        ee.setMaxt(exRecVO.getMaxt());
        Result<Long> saveRS = exRecService.create(ee);
        if(!saveRS.isSuccess() || null==saveRS.getData()){
            promoteException(saveRS.getCode(), saveRS.getMessage());
        }
        return saveRS.getData();
    }
}
