package com.bw.edu.ctb.common.qo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PPracBatchQO extends Paging implements Serializable {
    private Long userId;
    private Long dagangVersion;
    private Integer grade;
    private Integer classId;
    private Integer unitId;
    private Date gmtModified;
}
