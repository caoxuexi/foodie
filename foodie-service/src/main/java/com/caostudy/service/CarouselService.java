package com.caostudy.service;

import com.caostudy.pojo.Carousel;

import java.util.List;

/**
 * @author 曹学习
 * @description CarouselService
 * @date 2020/11/4 11:30
 */
public interface CarouselService {
    /**
     * 查询所有的轮播图
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow);
}
