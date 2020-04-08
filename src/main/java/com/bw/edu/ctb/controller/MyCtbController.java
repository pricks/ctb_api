package com.bw.edu.ctb.controller;

import com.bw.edu.ctb.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/myctb")
public class MyCtbController {

    @GetMapping("{id}")
    public Result getMyCtb(@PathVariable("id") Integer id){
        if( null == id ){
            return Result.failure(001, "参数为空");
        }

        Map map = new HashMap();
        map.put("ctbList", 123);
        return Result.success(map);
    }
}
