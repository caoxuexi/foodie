package com.caostudy.enums;

/**
 * @author 曹学习
 * @description PayMethod
 * @date 2020/11/12 17:55
 */
public enum PayMethod {
    WEIXIN(1,"微信"),
    ALIPAY(2,"支付宝");

    public final Integer type;
    public final String value;

    PayMethod(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
