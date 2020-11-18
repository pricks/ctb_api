package com.bw.edu.ctb.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 */
@Data
public class TgEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long dg;
    private Long gd;
    private Long cl;
    private String uk;//md5
    private String tgrp;
    private Long aid;
    private String aname;
}
