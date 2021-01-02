package com.bw.edu.ctb.common.util;

import java.util.List;

public class StringUtil {
    public static boolean isEmpty(String s){
        return null==s || "".equals(s.trim());
    }
    public static boolean isNotEmpty(String s){
        return null!=s && !"".equals(s.trim());
    }

    /** 如果字符串的值为'null'，也会被当做空值对待 */
    public static boolean isWideEmpty(String s){
        return null==s || "".equals(s.trim()) || "null".equalsIgnoreCase(s.trim());
    }
    public static boolean isNotWideEmpty(String s){
        return null!=s && !"".equals(s.trim()) && !"null".equalsIgnoreCase(s.trim());
    }

    /** 从0位开始截取长度为n的子字符串 */
    public static String subString(String s, int n){
        if(isEmpty(s)) return null;
        int size = s.length();
        if(size <= n) return s;
        return s.substring(0, n);
    }
}
