package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.dao.entity.SGEntity;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.dto.SsDTO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.service.ExSttService;
import com.bw.edu.ctb.service.TTService;
import com.bw.edu.ctb.service.UnitService;
import com.bw.edu.ctb.util.CollectionUtil;
import com.bw.edu.ctb.web.vo.ExRecVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mr")
public class MRCController {
    private final static Logger logger = LoggerFactory.getLogger(MRCController.class);

    @Autowired
    private UnitService unitService;
    @Autowired
    private ExSttService exSttService;
    @Autowired
    private TTService ttService;

    /** commit rv_rec */
    @PostMapping("/c")
    public Result<List<SsDTO>> tijiao_rv_record(ExRecVO exRec, HttpServletRequest request){
        try{
            String token = request.getParameter("atk");
            Result<BUsr> bUsrRS = usrService.getByAtk(token);
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }

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
}
