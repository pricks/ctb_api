package com.bw.edu.ctb.web.controller.ctb;


import com.bw.edu.ctb.common.util.QCosUtil;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
     * upload file 4 tinymce
     * */
    @RequestMapping(value = {"upload", "upload/"}, method = RequestMethod.POST)
//    @ResponseBody
    @CrossOrigin
    public Map<String, String> fileUpload4tm(@RequestParam("file") MultipartFile file){
        Map<String, String> result = new HashMap<>();

        if(file.isEmpty()){
            result.put("success", "0");
            return result;
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        try {

            String url = QCosUtil.uploadFileFromInputStream(fileName, (FileInputStream)file.getInputStream());
            System.out.println("url===="+url);

            //固定值
            result.put("uploaded", "1");
            result.put("file", url);
            //必须这样{"uploaded":"1","url", "图片URL"} 或者自己修改CKEditor5 js源代码
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }



    /**
     * upload file 4 ctb
     * */
    @RequestMapping(value = {"uct", "uct/"}, method = RequestMethod.POST)
//    @ResponseBody
    @CrossOrigin
    public Map<String, String> fileUpload4Ctb(@RequestParam("ct") MultipartFile file, HttpServletRequest request){
        Map<String, String> result = new HashMap<>();

        if(file.isEmpty()){
            result.put("success", "0");
            result.put("errCode", "file_null");
            result.put("errMsg", "上传的图片为空");
            return result;
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        try {

//            String url = QCosUtil.uploadFileFromInputStream(fileName, (FileInputStream)file.getInputStream());
//            System.out.println("url===="+url);

            //固定值
            result.put("success", "1");
//            result.put("file", url);
            //必须这样{"uploaded":"1","url", "图片URL"} 或者自己修改CKEditor5 js源代码
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", "0");
            result.put("errCode", "sys_error");
            result.put("errMsg", "系统异常");
            return result;
        }
    }


    /**
     * upload file 4 scratch
     * */
    @RequestMapping(value = {"uploadSC", "uploadSC/"}, method = RequestMethod.POST)
//    @ResponseBody
    @CrossOrigin
    public Map<String, String> fileUpload4Scratch(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Map<String, String> result = new HashMap<>();

        if(file.isEmpty()){
            result.put("success", "0");
            return result;
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        try {

//            String url = QCosUtil.uploadFileFromInputStream(fileName, (FileInputStream)file.getInputStream());
//            System.out.println("url===="+url);

            //固定值
            result.put("uploaded", "1");
//            result.put("file", url);
            //必须这样{"uploaded":"1","url", "图片URL"} 或者自己修改CKEditor5 js源代码
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }


    @GetMapping("/download/{id}")
    public void download(@PathVariable(value = "id") String id, HttpServletResponse response) {
        String filename="Scratch作品 (2) (1).sb3";
        String filePath = "/Users/fengyafei/Downloads";
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));

                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    os.flush();
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
                os.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally {
                bis = null;
                fis = null;
                os = null;
            }
        }
    }


    /**
     * upload file 4 ckeditor
     * */
    @RequestMapping(value = {"upload4CK", "upload/"}, method = RequestMethod.POST)
//    @ResponseBody
    @CrossOrigin
    public Map<String, String> fileUpload4CK(@RequestParam("upload") MultipartFile file){
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
