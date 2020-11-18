package com.bw.edu.ctb.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * id, gc
 * uid
 * dg, gd, cl: dagang, grade, class
 * un: unit code
 * rd: round
 * dl: difficulty_level
 * batch_id
 * ttids, kns
 * w_kns: wrong-answered kn id set
 * w_tts: wrong-answered tt id set
 * correct_max_kn
 */
@Data
public class ExRecEntity {
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
    private Long batchId;
    private String tts;
    private String kns;
    private String wkns;
    private String wtts;
    private Long cmkn;
}
