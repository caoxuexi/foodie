package com.caostudy.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 曹学习
 * @description UserBo
 * @date 2020/11/2 10:44
 */
@Getter
@Setter
@ApiModel(value = "用户对象BO",description = "从客户端，由用户传入的数据封装在此entity")
public class UserBO {
    @ApiModelProperty(value = "用户名",name = "username",example = "caoqiang",required = true)
    private String username;
    @ApiModelProperty(value = "密码",name = "password",example = "123123",required = true)
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",example = "123123",required = false)
    private String confirmPassword;
}
