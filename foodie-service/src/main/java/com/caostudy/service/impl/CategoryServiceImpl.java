package com.caostudy.service.impl;

import com.caostudy.enums.CategoryType;
import com.caostudy.mapper.CategoryMapper;
import com.caostudy.mapper.CategoryMapperCustom;
import com.caostudy.pojo.Carousel;
import com.caostudy.pojo.Category;
import com.caostudy.pojo.vo.CategoryVO;
import com.caostudy.pojo.vo.NewItemsVO;
import com.caostudy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 曹学习
 * @description CategoryServiceImpl
 * @date 2020/11/4 11:57
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example=new Example(Category.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("type", CategoryType.firstCategory.type);
        List<Category> result = categoryMapper.selectByExample(example);
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemLazy(Integer rootCatId) {
        Map<String,Object> map=new HashMap<>();
        map.put("rootCatId",rootCatId);
        return categoryMapperCustom.getSixNewItemsLazy(map);
    }


}
