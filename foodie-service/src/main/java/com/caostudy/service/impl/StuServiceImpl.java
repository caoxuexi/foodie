package com.caostudy.service.impl;

import com.caostudy.service.StuService;
import com.caostudy.mapper.StuMapper;
import com.caostudy.pojo.Stu;
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
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveStu() {
        Stu stu=new Stu();
        stu.setName("cao");
        stu.setAge(18);
        stuMapper.insert(stu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)

    public void updateStu(int id) {
        Stu stu=new Stu();
        stu.setName("caoqiang");
        stu.setAge(20);
        stu.setId(id);
        stuMapper.updateByPrimaryKey(stu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }
}
