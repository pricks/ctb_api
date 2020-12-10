package com.bw.edu.ctb.util;

import java.util.Date;
import java.util.UUID;

public class TokenGenUtil {
    public static String genToken(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");//32位长度
        String timestamp = String.valueOf(System.currentTimeMillis());//加上时间戳，防止uuid碰撞
        return StringUtil.subString(uuid+timestamp, 64);
    }
}
