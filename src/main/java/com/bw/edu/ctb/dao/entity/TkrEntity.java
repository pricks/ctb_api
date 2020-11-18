package com.bw.edu.ctb.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 */
@Data
public class TkrEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long kpId;
    private String bl;
    private Integer dl;
    private Long tid;
}
