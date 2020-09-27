package com.bw.edu.ctb.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PPracBatchEntity {
    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private Long userId;
    private Integer status;
    private String titleIds;

    private Integer dagangVersion;
    private Integer grade;
    private Integer classId;
    private Integer unitId;//单元
}
