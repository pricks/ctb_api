package com.bw.edu.ctb.common.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {
    public static boolean isEmpty(List list){
        return null==list || list.size()==0;
    }

    public static boolean isNotEmpty(List list){
        return null!=list && list.size()>0;
    }

    /**
     * 将字符串数组转换成Long类型的数组
     * @param sl
     * @return
     */
    public static List<Long> transfer(List<String> sl){
        if(isEmpty(sl)) return null;
        List<Long> rs = new ArrayList<>(sl.size());
        for(String str : sl){
            rs.add(Long.valueOf(str.trim()));
        }
        return rs;
    }
}
