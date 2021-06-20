package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.enums.DlEnum;
import com.bw.edu.ctb.common.enums.EokEnum;
import com.bw.edu.ctb.common.enums.StatusEnum;
import com.bw.edu.ctb.common.enums.subjects.TTypeEnum;
import com.bw.edu.ctb.common.qo.CtbTtQO;
import com.bw.edu.ctb.common.util.*;
import com.bw.edu.ctb.dao.entity.CtbTtEntity;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.domain.EBatch;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.service.CtbTtService;
import com.bw.edu.ctb.service.usr.UsrService;
import com.bw.edu.ctb.web.controller.common.TTBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;


@RestController
@RequestMapping("/ct")
public class MCTController {
    private final static Logger logger = LoggerFactory.getLogger(MCTController.class);

    @Autowired
    private CtbTtService ctbTtService;
    @Autowired
    private UsrService usrService;

    /** get user's ctb tt list */
    @PostMapping("cl")
    public Result ctb_list(CtbTtQO qo, HttpServletRequest request){
        try{
            logger.error("glu. r=" + request.getRemoteAddr());
            logger.error("=======ctb qo="+qo);

            //verify
            if(null==qo || StringUtil.isEmpty(qo.getAtk())
                    || null==qo.getUn()){
                return Result.failure();
            }
            Result<BUsr> bUsrRS = usrService.getByAtk(qo.getAtk());
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }
            qo.setUid(bUsr.getId());

            //query
            Result<List<TTEntity>> ctbRS = ctbTtService.queryTts(qo);
            if(!ctbRS.isSuccess() || CollectionUtil.isEmpty(ctbRS.getData())){
                return Result.failure("NODATA","无数据");
            }

            EBatch eb = TTBuilder.build(null, null, ctbRS.getData());
            eb.addProp("maxGm", qo.getMaxGm());
            return Result.success(eb);
        }catch (CtbException e){
            logger.error("cl biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("cl sys-error. qo="+qo, e);
            return Result.failure();
        }
    }


    /**
     * upload file 4 ctb
     * */
    @RequestMapping(value = {"u", "u/"}, method = RequestMethod.POST)
    @CrossOrigin
    public Result<String> fileUpload4Ctb(@RequestParam("ct") MultipartFile file, HttpServletRequest request){
        logger.error("ct_u. r=" + request.getRemoteAddr());

        /**
         * 1. 校验
         * 2. 限流--QPS限流、IP限流、用户限流
         * 3. 上传图片总数控制--按天限制，最多5张，多了的话tecent的COS流量费会很高
         * 4. 幂等
         * 5. 图片压缩和上传
         * 6. 写DB
         */

        try {
            // 1.文件空校验
            checkFile(file);


            // 2.用户token和入参校验
            Long uid = checkToken(request);


            // 3.限流--QPS限流、IP限流、用户限流
            // todo 这里先加一个全局的QPS限流...IP和用户维度的后面再通过redis添加
            AccessControl.ctbUploadLimite();


            // 4.上传图片总数限制--按天限制，最多5张，多了的话tecent的COS流量费会很高
            checkTotalUploads(uid);

            // 5.幂等 todo


            // 6.图片压缩和上传 todo 等抽空加一下，优先完成ap上架
            String fileName = file.getOriginalFilename();
            String url = QCosUtil.uploadFileFromInputStream(fileName, (FileInputStream)file.getInputStream());
            //test
//            String url = "https://ctb-1302713482.cos.ap-shanghai.myqcloud.com/1624072571839-Ct1kR5gDymhj2456d6738052500272951d2a22be2319.png";

            // 7.写DB
            saveCtb(request, uid, url);


            return Result.success();
        } catch (CtbException e){
            logger.error("ct_u biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("ct_u sys-error. file="+file, e);
            return Result.failure();
        }
    }

    private void saveCtb(HttpServletRequest request, Long uid, String url) {
        String unStr = request.getParameter("un");
        Long un = Long.valueOf(unStr);
        TTEntity t = new TTEntity();
        t.setUn(Long.valueOf(un));
        t.setDl(DlEnum.MOST_WRONG.getCode());
        t.setTc("<img width='100%' src=\""+url+"\">");
        t.setEok(EokEnum.KP_DETAIL.getCode());
        t.setType(TTypeEnum.WENDA.getCode());
        t.setAid(uid);
        t.setOi(true);
        t.setTs(StatusEnum.PULISHED.getCode());


        CtbTtEntity ctbTtEntity = new CtbTtEntity();
        ctbTtEntity.setTid(null);
        ctbTtEntity.setUid(uid);
        ctbTtEntity.setErrc(1);
        ctbTtEntity.setUn(un);
        ctbTtEntity.setSrc(1);

        ctbTtService.saveCtb(t, ctbTtEntity);
    }

    private void checkTotalUploads(Long uid) {
        Result<Integer> todayCountRS= ctbTtService.countToday(uid);
        if(!todayCountRS.isSuccess()){
            promoteException(todayCountRS.getCode(), todayCountRS.getMessage());
        }
        Integer tc = todayCountRS.getData();
        if(null == tc) tc = 0;
        //todo 此处应该加并发锁，针对用户维度
        if(tc > 5){
            promoteException(CtbExceptionEnum.FILE_COUNT_OVER);
        }
    }

    private Long checkToken(HttpServletRequest request) {
        String un = request.getParameter("un");
        if(StringUtil.isEmpty(un)){
            promoteException(CtbExceptionEnum.PARAM_NULL);
        }

        Long uid;
        String atk = request.getParameter("atk");
        if(StringUtil.isEmpty(atk)){
            promoteException(CtbExceptionEnum.USER_INFO_NULL);
        }
        Result<BUsr> bUsrRS = usrService.getByAtk(atk);
        BUsr bUsr = bUsrRS.getData();
        if(null==bUsr){
            logger.error("[hacker attach!] not existed usr");
            promoteException(CtbExceptionEnum.USER_INFO_NULL);
        }
        uid = bUsr.getId();
        return uid;
    }

    static int MAX_FILE_SIZE = 1024*1024;//1M
    private void checkFile( MultipartFile file ){
        if(file.isEmpty()){
            promoteException(CtbExceptionEnum.FILE_NULL);
        }
        int size = (int) file.getSize();
        if(size == 0){
            promoteException(CtbExceptionEnum.FILE_NULL);
        }
        if(size>MAX_FILE_SIZE){
            promoteException(CtbExceptionEnum.FILE_OVER_FLOW);
        }
    }
}
