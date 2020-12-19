package com.bw.edu.ctb.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static final String COMM_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static final DateFormat sdf = new SimpleDateFormat(COMM_DATE_FORMAT);

    public static String format(Date d){
        return sdf.format(d);
    }

    /** 为指定的日期增加指定的天数 */
    public static Date addDay(Date date, int diff) throws Exception{
        Calendar cd = Calendar.getInstance();
        cd.setTime(sdf.parse(sdf.format(date)));
        cd.add(Calendar.DATE, diff);//增加一天
        return cd.getTime();
    }


    public static void main(String[] args) throws Exception{
        Date now = new Date();
        System.out.println(format(new Date()));

        DateFormat sdf = new SimpleDateFormat(COMM_DATE_FORMAT);
        Calendar cd = Calendar.getInstance();
        cd.setTime(sdf.parse(sdf.format(new Date())));
        cd.add(Calendar.DATE, 30);//增加一天
        System.out.println(sdf.format(cd.getTime()));
    }
}
