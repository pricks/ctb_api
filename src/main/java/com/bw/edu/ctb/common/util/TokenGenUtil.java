package com.bw.edu.ctb.common.util;

import java.util.UUID;

public class TokenGenUtil {
    public static String genToken(){
        String uuid = UUID.randomUUID().toString().replaceAll("l", "");//32位长度
        String md5 = MD5Util.stringToMD5(String.valueOf(System.currentTimeMillis()));
        return StringUtil.subString(uuid+md5, 64);
    }

    public static String genToken(Long uid){
        String uuid = UUID.randomUUID().toString().replaceAll("l", "");//32位长度
        String md5 = MD5Util.stringToMD5(uid.toString());
        return StringUtil.subString(uuid+md5, 64);
    }
}
