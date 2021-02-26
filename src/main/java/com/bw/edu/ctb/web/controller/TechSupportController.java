package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.exception.CtbException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/home")
public class TechSupportController {
    @RequestMapping("index")
    public String support(HttpServletRequest request){
        try{
            return "技术支持请联系：340098052@qq.com";
        }catch (Exception e){
            return "系统异常";
        }
    }

    @RequestMapping("privatePolicy")
    public String private_policy(HttpServletRequest request){
        try{
            return "本app仅为纯粹的教育学习类app，目前未与任何第三方合作，请您放心使用！";
        }catch (Exception e){
            return "系统异常";
        }
    }
}
