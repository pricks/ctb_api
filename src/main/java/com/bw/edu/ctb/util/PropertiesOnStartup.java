package com.bw.edu.ctb.util;

public class PropertiesOnStartup {
    public static void parse(){
        System.out.println("start parse prop");
        String ds = System.getProperty("ds");
        if(ds == null) return;


        String sslport = ds.substring(0,3);

        if(ds.length() < 3)return;
        ds = ds.substring(3);
        int idx = ds.indexOf("HP");
        if(idx <= 0)return;
        String ip1 = ds.substring(0, idx);
        ds = ds.substring(idx + 2);

        if(ds.length() < 9)return;
        String webport = ds.substring(0,4);
        String ip2 = ds.substring(4,6);
        ds = ds.substring(9);
        idx = ds.indexOf("p3p");
        if(idx <=0)return;
        String sslkspwd = ds.substring(0,idx);

        ds = ds.substring(idx+3);
        idx = ds.indexOf("du");
        if(idx<=0)return;
        String ip3 = ds.substring(0, idx);

        ds = ds.substring(idx+2);
        idx = ds.indexOf("dp");
        if(idx<=0)return;
        String du = ds.substring(0, idx);

        ds = ds.substring(idx+2);
        idx = ds.indexOf("pp");
        if(idx<=0)return;
        String dp = ds.substring(0, idx);

        ds = ds.substring(idx+2);
        idx = ds.indexOf("skt");
        if(idx<=0)return;
        String ip4 = ds.substring(0,idx);

        ds = ds.substring(idx+3);
        String skt = ds;

        System.setProperty("server.ssl.key-store-password", sslkspwd);
        System.setProperty("server.port", sslport);
        System.setProperty("server.http-port", webport);
        System.setProperty("spring.datasource.jdbc-url", String.format("jdbc:mysql://%s.%s.%s.%s:3306/edu_ct?useUnicode=true&characterEncoding=utf-8",ip1,ip2,ip3,ip4));
        System.setProperty("spring.datasource.username", du);
        System.setProperty("spring.datasource.password", dp);
        System.setProperty("server.ssl.keyStoreType", skt);
        System.out.println("end parse prop");
    }

    public static void main(String[] args){
        String ds = "443119HP8";
        String sslport = ds.substring(0,3);

        if(ds.length() < 3)return;
        ds = ds.substring(3);
        int idx = ds.indexOf("HP");
        if(idx <= 0)return;
        String ip1 = ds.substring(0, idx);
        ds = ds.substring(idx + 2);

        if(ds.length() < 9)return;
        String webport = ds.substring(0,4);
        String ip2 = ds.substring(4,6);
        ds = ds.substring(9);
        idx = ds.indexOf("p3p");
        if(idx <=0)return;
        String sslkspwd = ds.substring(0,idx);

        ds = ds.substring(idx+3);
        idx = ds.indexOf("du");
        if(idx<=0)return;
        String ip3 = ds.substring(0, idx);

        ds = ds.substring(idx+2);
        idx = ds.indexOf("dp");
        if(idx<=0)return;
        String du = ds.substring(0, idx);

        ds = ds.substring(idx+2);
        idx = ds.indexOf("pp");
        if(idx<=0)return;
        String dp = ds.substring(0, idx);

        ds = ds.substring(idx+2);
        idx = ds.indexOf("skt");
        if(idx<=0)return;
        String ip4 = ds.substring(0,idx);

        ds = ds.substring(idx+3);
        String skt = ds;

        System.out.println("===");
    }
}
