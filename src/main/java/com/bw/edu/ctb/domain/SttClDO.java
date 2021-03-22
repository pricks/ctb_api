package com.bw.edu.ctb.domain;

import com.bw.edu.ctb.common.enums.DlEnum;
import com.bw.edu.ctb.common.util.DateUtil;
import com.bw.edu.ctb.dao.entity.ExRecEntity;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SttClDO implements Serializable {
    private Integer dg;
    private Integer gd;
    private Integer cl;
    private List<SttUnDO> tops;
    private List<SttUnDO> uns;

    /**
     * 获取指定单元下的dl="基础"的统计信息
     * @param un
     * @return
     */
    public SttDlDO getBasicDl(Long un){
        List<SttDlDO> sttDlDOS = null;
        for(SttUnDO su : uns){
            if(su.getUn().equals(un)){
                sttDlDOS = su.getDls();
                break;
            }
        }
        if(sttDlDOS==null){
            return null;
        }
        for(SttDlDO sd : sttDlDOS){
            if(DlEnum.BASIC.getCode().equals(sd.getDl())){
                return sd;
            }
        }
        return null;
    }

    public void update(ExRecEntity ee, KptBatchEntity kb){
        Long un = ee.getUn();
        Integer dl = ee.getDl();
        for(SttUnDO su : uns){
            if(su.getUn().equals(un)){
                List<SttDlDO> dls = su.getDls();
                for(SttDlDO sd : dls){
                    if(sd.getDl().equals(dl)){
                        sd.setActive(true);

                        //练习次数和最早练习时间
                        Integer ec = sd.getEc();
                        if(null==ec || ec.equals(0)){
                            ec=0;
                            sd.setGc(DateUtil.format(new Date()));
                        }
                        sd.setEc(ec+1);

                        sd.setMaxKpId(ee.getMaxk());
                        sd.setMaxTid(ee.getMaxt());
                        sd.setLscore(ee.getScore());//最后一次得分

                        //计算总分
                        if(ec.equals(0)){
                            sd.setAscore(ee.getScore());
                        }else{
                            sd.setAscore(sd.getAscore()+ee.getScore());
                        }

                        //计算已review的kp detail
                        Integer rkp=sd.getRkp();
                        if(null==rkp){
                            rkp=0;
                        }
                        sd.setRkp(rkp+ee.getCkc());

                        //是否已结束全部review
                        if(null==kb){
                            sd.setOver(true);
                            sd.setEc(0);
                            sd.setRd(sd.getRd()+1);//sd.rd从一开始就不为空
                            sd.setRkp(0);//置为0
                        }else{
                            sd.setOver(false);
                        }


                        //计算总体进度 todo 本次不做




                        //计算错误次数top3的kp detail todo 本次不做
                    }
                }
            }
        }
    }

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

    public static SttClDO buildEmpty(List<UnitEntity> ues, UnitDO unitDO){
        if(null==ues || ues.size()==0) return null;
        SttClDO cl = new SttClDO();
        cl.setDg(unitDO.getDg());
        cl.setGd(unitDO.getGd());
        cl.setCl(unitDO.getCl());

        List<SttUnDO> uns = new ArrayList<>(ues.size());
        for(UnitEntity ue: ues){
            SttUnDO un = new SttUnDO();
            un.setRd(1);
            un.setUn(ue.getCode());
            un.setUname(ue.getName());

            List<SttDlDO> dls = new ArrayList<>(DlEnum.size());
            for(DlEnum de : DlEnum.values()){
                SttDlDO d = SttDlDO.newInstance().dl(de.getCode())
                                .dlname(de.getName()).rd(1).tkp(100)
                                .rkp(0).ekp(0).lscore(0).ec(0);
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

    public Integer getDg() {
        return dg;
    }

    public void setDg(Integer dg) {
        this.dg = dg;
    }

    public Integer getGd() {
        return gd;
    }

    public void setGd(Integer gd) {
        this.gd = gd;
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
