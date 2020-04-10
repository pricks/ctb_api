package com.bw.edu.ctb.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.dto.CtbDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return Result.success(buildMyCtbList());
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
