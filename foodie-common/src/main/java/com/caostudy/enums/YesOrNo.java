package com.caostudy.enums;

/**
 * @author 曹学习
 * @description 是否枚举
 * @date 2020/11/2 11:00
 */
public enum YesOrNo {
    No(0,"否"),
    YES(1,"是");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
