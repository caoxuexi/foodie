package com.caostudy.service;

import com.caostudy.pojo.Stu;

/**
 * @author 曹学习
 * @description StuService
 * @date 2020/11/1 19:15
 */
public interface StuService {
    public Stu getStuInfo(int id);
    public void saveStu();
    public void updateStu(int id);
    public void deleteStu(int id);
}
