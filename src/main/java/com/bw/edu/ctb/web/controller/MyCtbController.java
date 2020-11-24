package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.TitleQO;
import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import com.bw.edu.ctb.dao.mapper.CtbTitleMapper;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.web.vo.TitleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

@RestController
@RequestMapping("/myctb")
public class MyCtbController {
    private final static Logger logger = LoggerFactory.getLogger(MyCtbController.class);

    @Autowired
    private CtbTitleMapper titleMapper;

    @RequestMapping("quetry")
//    @PostMapping("/quetry")
    public Result queryTitle(TitleQO titleQO, HttpServletRequest request){
        logger.error("query title. raddr=" + request.getRemoteAddr());
        Result titleDTOResult = new Result();
        List<CtbTitleEntity> titleEntityList = titleMapper.selectByPage(titleQO);
        return Result.success(titleEntityList);
    }

    @GetMapping("getT/{id}")
    public Map getTitle(@PathVariable("id") Integer id){
        if( null == id ){
            return null;
        }

        Map<String, String> rs = new HashMap<>();
        rs.put("content", "<p>Content of the editor.</p><p>我的图片：</p><figure class=\"image\"><img src=\"https://ctb-1302713482.cos.ap-shanghai.myqcloud.com/1596857055623-00tesgt.jpg\"></figure><p>范德萨</p>");



        return rs;
    }

    /**
     * upload title
     * */
    @PostMapping("/save")
    @CrossOrigin
    public Map<String, String> saveTitle(@RequestBody TitleVO titleVO){
        Map<String, String> result = new HashMap<>();

        try {
            //1.校验
            checkTitle(titleVO);

            //2.convert
            CtbTitleEntity titleEntity = convert(titleVO);

            //3.save
            int saveRs = titleMapper.save(titleEntity);
            if(saveRs <= 0){
                System.out.println("保存失败");
            }
            //必须这样{"uploaded":"1","url", "图片URL"} 或者自己修改CKEditor5 js源代码
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    private CtbTitleEntity convert(TitleVO titleVO){
        CtbTitleEntity ctbTitleEntity = new CtbTitleEntity();
        BeanUtils.copyProperties(titleVO, ctbTitleEntity);
        ctbTitleEntity.setAuthorId(1L);
        ctbTitleEntity.setAuthorName("system");
        ctbTitleEntity.setCommentsCount(0);
        ctbTitleEntity.setShortContent(cutContent(titleVO.getContent()));
        ctbTitleEntity.setCovers(extractCover(titleVO.getContent()));
        ctbTitleEntity.setClassType(titleVO.getClassName());
        return ctbTitleEntity;
    }

    private String cutContent(String cont){
        if(null == cont) return null;
        cont = cont.trim();
        if(cont.length() <= 50) return cont;
        return cont.substring(0, 50);
    }
    private String extractCover(String cont){
        if(null == cont) return null;
        cont = cont.substring(cont.indexOf("<img")+4);
        cont = cont.trim();
        if(cont.startsWith("src")){
            cont = cont.substring(cont.indexOf("src=\"")+5);
            cont = cont.substring(0, cont.indexOf("\""));
            return cont;
        }
        return null;
    }

    private void checkTitle(TitleVO titleVO){
        if(null == titleVO){
            promoteException(CtbExceptionEnum.OBJECT_NULL, "titleVO="+titleVO);
        }
        if(null == titleVO.getClassName()){
            promoteException(CtbExceptionEnum.CLASS_IS_NULL, "titleVO="+titleVO);
        }
        if(null == titleVO.getContent() || "".equals(titleVO.getContent().trim())){
            promoteException(CtbExceptionEnum.CONTENT_IS_NULL, "titleVO="+titleVO);
        }
        if(null == titleVO.getAnswer() || "".equals(titleVO.getAnswer().trim())){
            promoteException(CtbExceptionEnum.ANSWER_IS_NULL, "titleVO="+titleVO);
        }
        if(null == titleVO.getGrade()){
            promoteException(CtbExceptionEnum.GRADE_IS_NULL, "titleVO="+titleVO);
        }
        if(null == titleVO.getType()){
            promoteException(CtbExceptionEnum.TYPE_IS_NULL, "titleVO="+titleVO);
        }
        if(null == titleVO.getRegion()){
            titleVO.setRegion(1);
        }
        if(null == titleVO.getDagang()){
            titleVO.setDagang(1);
        }
    }
}
