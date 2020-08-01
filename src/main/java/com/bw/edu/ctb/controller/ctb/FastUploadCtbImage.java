package com.bw.edu.ctb.controller.ctb;


import com.bw.edu.ctb.util.QCosUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/myctb/fuci")
@CrossOrigin
public class FastUploadCtbImage {
    /*
     * get file
     */
    @RequestMapping("file")
    public String file(){
        return "/file";
    }

    /**
     * upload file
     * */
    @RequestMapping(value = {"upload", "upload/"}, method = RequestMethod.POST)
//    @ResponseBody
    @CrossOrigin
    public Map<String, String> fileUpload(@RequestParam("upload") MultipartFile file){
        Map<String, String> result = new HashMap<>();

        if(file.isEmpty()){
            result.put("success", "0");
            return result;
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
//        System.out.println(fileName + "-->" + size);
//
//        String path = "/Users/zaichen/Downloads";
//        File dest = new File(path + "/" + fileName);
//        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
//            dest.getParentFile().mkdir();
//        }
        try {
//            file.transferTo(dest); //保存文件

            String url = QCosUtil.uploadFileFromInputStream(fileName, (FileInputStream)file.getInputStream());
            System.out.println("url===="+url);

            //固定值
            result.put("uploaded", "1");
            result.put("url", url);
            //必须这样{"uploaded":"1","url", "图片URL"} 或者自己修改CKEditor5 js源代码
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    /**
     * 获取multifile.html页面
     */
    @RequestMapping("multifile")
    public String multifile(){
        return "/multifile";
    }

    /**
     * 实现多文件上传
     * */
    /**public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files) */
    @RequestMapping(value="multifileUpload",method= RequestMethod.POST)
    public @ResponseBody String multifileUpload(HttpServletRequest request){

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");

        if(files.isEmpty()){
            return "false";
        }

        String path = "F:/test" ;

        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if(file.isEmpty()){
                return "false";
            }else{
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }
}
