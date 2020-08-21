package com.bw.edu.ctb.common.qo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Paging implements Serializable {

    private static final long serialVersionUID = -4619166274607836134L;

    /**
     * 分页查询，单页最大查询数据量
     */
    public static final int MAX_PAGE_SIZE = 200;

    // 开始位置--分页使用
    Integer begin;

    // 记录数--分页使用
    Integer num;

    // 总记录数--分页查询使用
    int totalNum;

    //排序字段
    String sortProperty;

    //升降序
    String sortMode;

    public Integer getBegin() {
        if(null == begin || begin < 0){
            return 0;
        }
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getNum() {
        if(null == num || num <= 0){
            return MAX_PAGE_SIZE;
        }
        return num;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public String getSortMode() {
        if(null == sortMode || "".equals(sortMode.trim())){
            return "ASC";
        }
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}