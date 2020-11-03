package com.caostudy.enums;

/**
 * @author 曹学习
 * @description Sex
 * @date 2020/11/2 11:00
 */
public enum Sex {
    woman(0,"女"),
    man(1,"男"),
    secret(2,"保密");

    public final Integer type;
    public final String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
