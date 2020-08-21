package com.bw.edu.ctb.common.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TitleQO extends Paging implements Serializable {
    private Integer grade;
    private Integer classType;
    private Integer type;
    private Integer dagang;
    private Integer region;
}
