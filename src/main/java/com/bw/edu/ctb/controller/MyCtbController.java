package com.bw.edu.ctb.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.dto.CtbDTO;
import com.bw.edu.ctb.dto.CtbDTO2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/myctb")
public class MyCtbController {

    @GetMapping("{id}")
    public Result getMyCtb(@PathVariable("id") Integer id){
        if( null == id ){
            return Result.failure(001, "参数为空");
        }

        //test 上传图片到cos


        return Result.success(buildMyCtbList2());
    }

    /**
     * 实现文件上传
     * */
    @RequestMapping("form")
    @ResponseBody
    public String formSubmit(@RequestParam("title") String title,
                             @RequestParam("module") String module,
                             @RequestParam("content") String content){
        System.out.println(title);
        return "hello";
    }

    private List<CtbDTO2> buildMyCtbList2(){
        DateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        List<CtbDTO2> ctbDTOList = new ArrayList<>();

        for(int i = 0; i < 15; i++){
            CtbDTO2 ctbDTO = new CtbDTO2();
            ctbDTO.setId(Long.valueOf(i));
            ctbDTO.setTitle("20以内加减法错误" + i);
            ctbDTO.setPublishedAt(df.format(new Date()));
            ctbDTO.setAuthorName("审理三");
            ctbDTO.setCommentsCount(i*2+(i*3+5));
            ctbDTO.setPostId(Long.valueOf(i+9));
            ctbDTOList.add(ctbDTO);
        }
        return ctbDTOList;
    }

    private List<CtbDTO> buildMyCtbList(){
        DateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        List<CtbDTO> ctbDTOList = new ArrayList<>();

        for(int i = 0; i < 15; i++){
            CtbDTO ctbDTO = new CtbDTO();
            ctbDTO.setId(Long.valueOf(i));
            ctbDTO.setTitle("20以内加减法错误" + i);
            ctbDTO.setPostTime(df.format(new Date()));
            ctbDTO.setAuthor("我");
            ctbDTOList.add(ctbDTO);
        }
        return ctbDTOList;
    }
}
