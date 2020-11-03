package com.caostudy.service.impl;

import com.caostudy.enums.Sex;
import com.caostudy.mapper.UsersMapper;
import com.caostudy.pojo.Users;
import com.caostudy.pojo.bo.UserBO;
import com.caostudy.service.UserService;
import com.caostudy.utils.DateUtil;
import com.caostudy.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author 曹学习
 * @description StuServiceImpl
 * @date 2020/11/1 19:22
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    //默认头像网上随便找一个
    public static final String USER_FACE="https://img.zcool.cn/community/01c8415b10ec7aa801212d57334fdc.png@2o.png";

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryUsernameIsExist(String username) {
        Example userExample= new Example(Users.class);
        Example.Criteria userCriteria=userExample.createCriteria();
        //第一个参数为数据库字段名  第二个是我们的值
        userCriteria.andEqualTo("username",username);
        Users result = usersMapper.selectOneByExample(userExample);
        return result == null?false:true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Users createUser(UserBO userBO) {
        String userId=sid.nextShort();
        Users user=new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认昵称同用户名
        user.setNickname(userBO.getUsername());
        //默认头像
        user.setFace(USER_FACE);
        //设置默认生日
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        //默认性别为保密
        user.setSex(Sex.secret.type);
        user.setUpdatedTime(new Date());
        user.setCreatedTime(new Date());
        usersMapper.insert(user);
        return user;
    }
}
