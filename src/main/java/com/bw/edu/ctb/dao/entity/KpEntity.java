package com.bw.edu.ctb.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * id, gc, gm
 * dg, gd, cl, un, dl: dagang, grade, class, unit,difficulty_level
 * p_id: the parent id
 * point: varchar
 * content
 * command
 */
@Data
public class KpEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long dg;
    private Long gd;
    private Long cl;
    private Long un;
    private Long dl;
    private Long pid;
    private String point;
    private String content;
    private String command;
}
