package com.bw.edu.ctb.common.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TTQO extends Paging implements Serializable {
    private Long id;
    private Long un;
    private Integer dl;
    private Integer eok;
}
