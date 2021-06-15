package com.bw.edu.ctb.common.enums.subjects;

import java.util.ArrayList;
import java.util.List;

/**
 * relation of dagang & grade
 */
public enum DGRel {
    /**====================== 人教版 =========================*/
    D1_X11(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X11),
    D1_X12(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X12),
    D1_X21(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X21),
    D1_X22(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X22),
    D1_X31(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X31),
    D1_X32(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X32),
    D1_X41(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X41),
    D1_X42(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X42),
    D1_X51(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X51),
    D1_X52(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X52),
    D1_X61(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X61),
    D1_X62(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X62),


    D1_C1(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.C1),
    D1_C2(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.C2),
    D1_C3(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.C3),

    ;

    public static List<GradeEnum> getGrade(Integer dagangCode){
        List<GradeEnum> gradeEnums = new ArrayList<>();
        for(DGRel e : DGRel.values()){
            if(e.getCode().equals(dagangCode)){
                gradeEnums.add(e.getGe());
            }
        }
        return gradeEnums;
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
