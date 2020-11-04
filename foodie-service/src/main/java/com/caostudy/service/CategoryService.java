package com.caostudy.service;

import com.caostudy.pojo.Category;
import com.caostudy.pojo.vo.CategoryVO;
import com.caostudy.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * @author 曹学习
 * @description CarouselService
 * @date 2020/11/4 11:30
 */
public interface CategoryService {
    /**
     * 查询所有的轮播图
     */
    public List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id查询子分类信息
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     */
    public List<NewItemsVO> getSixNewItemLazy(Integer rootCatId);
}
