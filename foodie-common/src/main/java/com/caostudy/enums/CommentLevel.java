package com.caostudy.enums;

/**
 * @author 曹学习
 * @description 评价等级 枚举
 * @date 2020/11/2 11:00
 */
public enum CommentLevel {
    GOOD(1,"好评"),
    NORMAL(2,"中评"),
    BAD(3,"差评");

    public final Integer type;
    public final String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
