package com.bw.edu.ctb.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CtbTitleDTO implements Serializable {
    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private String title;
    private String shortContent;
    private String content;
    private String answer;
    private String covers;
    private Integer type;
    private String authorName;
    private Long authorId;
    private Integer commentsCount;
    private Integer classType;
    private Integer grade;
    private Integer region;
    private Integer dagang;
}
