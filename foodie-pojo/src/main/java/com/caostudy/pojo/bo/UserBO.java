package com.caostudy.pojo.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 曹学习
 * @description UserBo
 * @date 2020/11/2 10:44
 */
@Getter
@Setter
public class UserBO {
    private String username;
    private String password;
    private String confirmPassword;
}
