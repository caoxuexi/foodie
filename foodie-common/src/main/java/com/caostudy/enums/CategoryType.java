package com.caostudy.enums;

/**
 * @author 曹学习
 * @description CategoryType
 * @date 2020/11/4 11:58
 */
public enum CategoryType {
    firstCategory(1,"一级大分类"),
    secondCategory(2,"二级分类"),
    thirdCategory(3,"三级小分类");

    public final Integer type;
    public final String value;

    CategoryType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
