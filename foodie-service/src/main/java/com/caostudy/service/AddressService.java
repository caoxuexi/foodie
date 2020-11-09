package com.caostudy.service;

import com.caostudy.pojo.UserAddress;
import com.caostudy.pojo.bo.AddressBO;

import java.util.List;

/**
 * @author 曹学习
 * @description AddressService
 * @date 2020/11/9 16:40
 */
public interface AddressService {

    /**
     * 根据用户id查询用户的收货地址
     * @param userId
     * @return
     */
    public List<UserAddress> queryAll(String userId);

    /**
     * 新增收货地址
     * @param addressBO
     */
    public void addNewUserAddress(AddressBO addressBO);

    /**
     * 修改收货地址
     * @param addressBO
     */
    public void updateUserAddress(AddressBO addressBO);
}
