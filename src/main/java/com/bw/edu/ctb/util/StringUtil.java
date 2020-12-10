package com.bw.edu.ctb.util;

public class StringUtil {
    public static boolean isEmpty(String s){
        return null==s || "".equals(s.trim());
    }

    /** 从0位开始截取长度为n的子字符串 */
    public static String subString(String s, int n){
        if(isEmpty(s)) return null;
        int size = s.length();
        if(size <= n) return s;
        return s.substring(0, n);
    }
}
