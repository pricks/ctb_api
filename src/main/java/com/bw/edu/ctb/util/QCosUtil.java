package com.bw.edu.ctb.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * tecent cos对象存储工具类
 */
public class QCosUtil {
    private static COSClient cosClient;

    /** 腾讯云COS对象存储的桶的name哈希表，用户运行期快速获取桶的信息，例如域名 */
    private static Map<String, Bucket> bucketMap = new ConcurrentHashMap<>();

    static{
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDPoHY2uhrdtwmogEAlBNwHQ7dgTCqqnpc";
        String secretKey = "h0cWfL8sg6td2C7mFQM1UhfnhLyNbfR3";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-shanghai");//地域简称
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端
        cosClient = new COSClient(cred, clientConfig);
        // 4 获取全量存储桶
        bucketMap = queryBucket();
    }


    public static void main(String[] args){
//        queryBucket();

        String url = getBucketUrl("ctb-1302713482");
        System.out.println("url==="+url);
    }

    /**
     * 查询全量存储桶
     */
    public static Map<String, Bucket> queryBucket(){
        Map<String, Bucket> tmp = new HashMap<>();
        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            tmp.put(bucketName, bucketElement);
        }
        return tmp;
    }
    /**
     * 根据桶的name查询桶对象
     * @param bucketName
     * @return
     */
    public static Bucket queryBucketByName(String bucketName){
        Bucket bucket = bucketMap.get(bucketName);
        if(null == bucket) {
            Map<String, Bucket> tmp = queryBucket(); //再查一次，防止用户又创建了一个桶
            bucket = tmp.get(bucketName);
            if(null != bucket){
                bucketMap = tmp;
            }
        }
        return bucket;
    }

    /**
     * 根据桶name，拼装桶的url
     * 具体桶的url规则见：
     *  https://cloud.tencent.com/document/product/436/6224
     *  https://cloud.tencent.com/document/product/436/30740
     * @param bucketName
     * @return
     */
    public static String getBucketUrl(String bucketName){
        Bucket bucket = queryBucketByName(bucketName);
        if(null == bucket){
            return null;
        }

        return new StringBuilder("https://").append(bucketName)
                .append(".cos.") //如果不加这个，表示下载
                .append(bucket.getLocation())
                .append(".myqcloud.com/")
                .toString();
    }

    public static String uploadFileFromInputStream(String fileName, FileInputStream fis) throws Exception{
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度为500
        objectMetadata.setContentLength(fis.available());
        // 设置 Content type, 默认是 application/octet-stream
        objectMetadata.setContentType("image/jpeg");

        String key = String.valueOf(new Date().getTime())+"-"+fileName;
        String bucketName = "ctb-1302713482";
        PutObjectResult putObjectResult = cosClient.putObject(bucketName, key, fis, objectMetadata);
//        etag = putObjectResult.getETag();
        return getBucketUrl(bucketName)+key;
    }

    /**
     * 上传本地文件
     */
    public static void uploadLocalFile(){
        // 指定要上传的文件
        String localFilePath = "/Users/zaichen/Downloads/title1.jpg";
        File localFile = new File(localFilePath);
        // 指定要上传到的存储桶
        String bucketName = "ctb-1302713482";
        // 指定要上传到 COS 上对象键
        String key = "title0003.jpg";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        System.out.println("cos rs ==" + putObjectResult);
    }
}
