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

    /**
     * 根据用户id和地址id，删除对应用户的地址信息
     * @param userId
     * @param addressId
     */
    public void deleteUserAddress(String userId,String addressId);

    /**
     * 根据用户id和地址id，修改对应用户的地址信息为默认地址
     * @param userId
     * @param addressId
     */
    public void updateUserAddressToDefault(String userId,String addressId);

    /**
     * 根据用户id和地址id， 查询具体的用户地址对象信息
     * @param userId
     * @param addressId
     * @return
     */
    public UserAddress queryUserAddress(String userId,String addressId);
}
