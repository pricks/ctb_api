package com.bw.edu.ctb.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * tc：content, can be blank. For reading comprehension, a passage of reading material will be given, and then several questions will be asked. For mathematics, there is usually no reading material. This field is mainly used to describe the reading material.
 * ts: status, 0-created, 1-reviewing, 2-published, 3-audit failed, -1-deleted(logical)
 * t_type：1-single-choice-question, 2-multiple-choice-question, 3-fill-in-blanks-question, 4-application-question, 5-reading-comprehension-question
 * oi: indicate the title is offline. The optional values are 1 and 0. If the title is offline, it has no alternative answers.Such as fill-in-the-blanks-test, Application-problem, can only be set to offline question type.
 * tgi: the group id that this title belongs to
 * ots: the alternative answers of this title. Only choice-tests have multiple optional answers.
 * tat: analysis_text
 * tav: analysis_video, varchar
 * tca: correct answer. varchar
 * aid, aname: author_id, author_name
 */
@Data
public class TTEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Integer dg;
    private Integer gd;
    private Integer cl;
    private Long un;
    private Long dl;
    private Long kp;
    private String tc;
    private Integer ts;
    private Integer tType;
    private Integer oi;
    private Long tgi;
    private String ots;
    private String tat;
    private String tav;
    private String tca;
    private Long aid;
    private String aname;
}
