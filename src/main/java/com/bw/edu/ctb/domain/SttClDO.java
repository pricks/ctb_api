package com.bw.edu.ctb.domain;

import com.bw.edu.ctb.common.enums.DlEnum;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SttClDO implements Serializable {
    private Integer cl;
    private List<SttUnDO> tops;
    private List<SttUnDO> uns;

    public static void main(String[] args){
        String ss = buildSST();
        try {
            SttClDO stt = new ObjectMapper().readValue(ss, SttClDO.class);
            SttUnDO su0 = stt.getTops().get(0);
            su0.setUn(99L);

            System.out.println(stt.getTops().get(0).getUn());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static String buildSST(){
        String ss = "{\n" +
                "\t\"cl\":1,\n" +
                "\t\"tops\":[\n" +
                "\t\t{\n" +
                "\t\t  \"un\": 121, \n" +
                "\t\t  \"uname\":\"un1dfdsa\",\n" +
                "\t\t  \"rd\":1,\n" +
                "\t\t  \"dls\":[\n" +
                "\t\t  \t{\n" +
                "\t\t\t    \"dl\": 1,\n" +
                "\t\t\t    \"active\":true,\n" +
                "\t\t\t    \"dlname\":\"basicKp\",\n" +
                "\t\t\t    \"tkp\": 25,\n" +
                "\t\t\t    \"rkp\": 12,\n" +
                "\t\t\t    \"ekp\": 21\n" +
                "\t\t  \t},\n" +
                "\t\t  \t{\n" +
                "\t\t\t    \"dl\": 1,\n" +
                "\t\t\t    \"dlname\":\"basicKp\",\n" +
                "\t\t\t    \"tkp\": 25,\n" +
                "\t\t\t    \"rkp\": 12,\n" +
                "\t\t\t    \"ekp\": 21\n" +
                "\t\t  \t}\n" +
                "\t\t  ]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t  \"un\": 122, \n" +
                "\t\t  \"uname\":\"un2dfdsa\",\n" +
                "\t\t  \"rd\":1,\n" +
                "\t\t  \"dls\":[\n" +
                "\t\t  \t{\n" +
                "\t\t\t    \"dl\": 1,\n" +
                "\t\t\t    \"active\":true,\n" +
                "\t\t\t    \"dlname\":\"basicKp\",\n" +
                "\t\t\t    \"tkp\": 25,\n" +
                "\t\t\t    \"rkp\": 12,\n" +
                "\t\t\t    \"ekp\": 21\n" +
                "\t\t  \t},\n" +
                "\t\t  \t{\n" +
                "\t\t\t    \"dl\": 2,\n" +
                "\t\t\t    \"active\":true,\n" +
                "\t\t\t    \"dlname\":\"basicKp\",\n" +
                "\t\t\t    \"tkp\": 25,\n" +
                "\t\t\t    \"rkp\": 12,\n" +
                "\t\t\t    \"ekp\": 21\n" +
                "\t\t  \t}\n" +
                "\t\t  ]\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"uns\":[\n" +
                "\t\t{\n" +
                "\t\t  \"un\": 121, \n" +
                "\t\t  \"uname\":\"un1dfdsa\",\n" +
                "\t\t  \"rd\":1,\n" +
                "\t\t  \"dls\":[\n" +
                "\t\t  \t{\n" +
                "\t\t\t    \"dl\": 1,\n" +
                "\t\t\t    \"active\":true,\n" +
                "\t\t\t    \"dlname\":\"basicKp\",\n" +
                "\t\t\t    \"tkp\": 25,\n" +
                "\t\t\t    \"rkp\": 12,\n" +
                "\t\t\t    \"ekp\": 21\n" +
                "\t\t  \t},\n" +
                "\t\t  \t{\n" +
                "\t\t\t    \"dl\": 1,\n" +
                "\t\t\t    \"dlname\":\"basicKp\",\n" +
                "\t\t\t    \"tkp\": 25,\n" +
                "\t\t\t    \"rkp\": 12,\n" +
                "\t\t\t    \"ekp\": 21\n" +
                "\t\t  \t}\n" +
                "\t\t  ]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t  \"un\": 122, \n" +
                "\t\t  \"uname\":\"un2dfdsa\",\n" +
                "\t\t  \"rd\":1,\n" +
                "\t\t  \"dls\":[\n" +
                "\t\t  \t{\n" +
                "\t\t\t    \"dl\": 1,\n" +
                "\t\t\t    \"active\":true,\n" +
                "\t\t\t    \"dlname\":\"basicKp\",\n" +
                "\t\t\t    \"tkp\": 25,\n" +
                "\t\t\t    \"rkp\": 12,\n" +
                "\t\t\t    \"ekp\": 21\n" +
                "\t\t  \t},\n" +
                "\t\t  \t{\n" +
                "\t\t\t    \"dl\": 1,\n" +
                "\t\t\t    \"dlname\":\"basicKp\",\n" +
                "\t\t\t    \"tkp\": 25,\n" +
                "\t\t\t    \"rkp\": 12,\n" +
                "\t\t\t    \"ekp\": 21\n" +
                "\t\t  \t}\n" +
                "\t\t  ]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        return ss;
    }

    public static SttClDO buildEmpty(List<UnitEntity> ues){
        if(null==ues || ues.size()==0) return null;
        SttClDO cl = new SttClDO();
        cl.setCl(ues.get(0).getCl());

        List<SttUnDO> uns = new ArrayList<>(ues.size());
        for(UnitEntity ue: ues){
            SttUnDO un = new SttUnDO();
            un.setRd(1);
            un.setUn(ue.getCode());
            un.setUname(ue.getName());

            List<SttDlDO> dls = new ArrayList<>(DlEnum.size());
            for(DlEnum de : DlEnum.values()){
                SttDlDO d = SttDlDO.newInstance().dl(de.getCode())
                                .dlname(de.getName()).tkp(100)
                                .rkp(0).ekp(0);
                if(de.getCode().equals(DlEnum.BASIC.getCode())){
                    d.active(true);
                }
                if(de.getCode().equals(DlEnum.EXERCISE.getCode())){
                    d.dr(0);
                }
                dls.add(d);
            }
            un.setDls(dls);

            uns.add(un);
        }

        cl.setUns(uns);
        return cl;
    }

    public Integer getCl() {
        return cl;
    }

    public void setCl(Integer cl) {
        this.cl = cl;
    }

    public List<SttUnDO> getTops() {
        return tops;
    }

    public void setTops(List<SttUnDO> tops) {
        this.tops = tops;
    }

    public List<SttUnDO> getUns() {
        return uns;
    }

    public void setUns(List<SttUnDO> uns) {
        this.uns = uns;
    }
}
