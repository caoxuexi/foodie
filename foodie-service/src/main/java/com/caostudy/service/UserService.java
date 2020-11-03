package com.caostudy.service;

import com.caostudy.pojo.Users;
import com.caostudy.pojo.bo.UserBO;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 曹学习
 * @description StuService
 * @date 2020/11/1 19:15
 */
public interface UserService {
    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     */
    public Users createUser(UserBO userBo);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    public Users queryUserForLogin(String username, String password);
}
