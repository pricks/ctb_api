package com.bw.edu.ctb.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 */
@Data
public class ExbatchEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long uid;
    private Long dg;
    private Long gd;
    private Long cl;
    private Long un;
    private Long dl;
    private Integer rd;
    private Integer tc;
    private Integer tgmc;
    private String kps;
    private String tts;
    private Integer status;
}
