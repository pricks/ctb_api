package com.bw.edu.ctb.common.enums.subjects;

import com.bw.edu.ctb.common.enums.DlEnum;

/**
 * relation of dagang & grade
 */
public enum DGRel {
    /**====================== 人教版 =========================*/
    D1_X1(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X1),
    D1_X2(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X2),
    D1_X3(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X3),
    D1_X4(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X4),
    D1_X5(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X5),
    D1_X6(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X6),
    D1_C1(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.C1),
    D1_C2(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.C2),
    D1_C3(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.C3),

    ;

    public GradeEnum getGrade(Integer dagangCode){
        for(DGRel e : DGRel.values()){
            if(e.getCode().equals(dagangCode)){
                return e.getGe();
            }
        }
        return null;
    }

    private Integer code;
    private GradeEnum ge;

    DGRel(Integer code, GradeEnum ge){
        this.code = code;
        this.ge = ge;
    }

    public Integer getCode() {
        return code;
    }

    public GradeEnum getGe() {
        return ge;
    }
}
