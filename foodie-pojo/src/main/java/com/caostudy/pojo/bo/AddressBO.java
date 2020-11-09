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
@ApiModel(value = "用户地址BO",description = "从客户端，由用户传入的数据封装在此entity")
public class AddressBO {
    @ApiModelProperty(value = "地址id",name = "addressId",example = "190825CG3AA14Y3C",required = true)
    private String addressId;
    @ApiModelProperty(value = "用户id",name = "userId",example = "1908189H7TNWDTXP",required = true)
    private String userId;
    @ApiModelProperty(value = "收货人",name = "receiver",example = "jack",required = true)
    private String receiver;
    @ApiModelProperty(value = "手机号码",name = "mobile",example = "13333333333",required = true)
    private String mobile;
    @ApiModelProperty(value = "省份",name = "province",example = "北京",required = true)
    private String province;
    @ApiModelProperty(value = "城市",name = "city",example = "北京",required = true)
    private String city;
    @ApiModelProperty(value = "地区",name = "district",example = "东城区",required = true)
    private String district;
    @ApiModelProperty(value = "详情",name = "detail",example = "123",required = true)
    private String detail;
}
