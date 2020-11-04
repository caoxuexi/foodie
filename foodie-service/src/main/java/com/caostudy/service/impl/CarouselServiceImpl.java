package com.caostudy.service.impl;

import com.caostudy.mapper.CarouselMapper;
import com.caostudy.pojo.Carousel;
import com.caostudy.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 曹学习
 * @description CarouselServiceImpl
 * @date 2020/11/4 11:32
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example=new Example(Carousel.class);
        example.orderBy("sort").desc();//默认是asc
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("isShow",isShow);
        List<Carousel> result = carouselMapper.selectByExample(example);
        return result;
    }
}
