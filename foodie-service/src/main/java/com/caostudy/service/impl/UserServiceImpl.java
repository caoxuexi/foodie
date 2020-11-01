package com.caostudy.service.impl;

import com.caostudy.mapper.StuMapper;
import com.caostudy.mapper.UsersMapper;
import com.caostudy.pojo.Stu;
import com.caostudy.service.StuService;
import com.caostudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 曹学习
 * @description StuServiceImpl
 * @date 2020/11/1 19:22
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean queryUsernameIsExist(String username) {
        return false;
    }
}
