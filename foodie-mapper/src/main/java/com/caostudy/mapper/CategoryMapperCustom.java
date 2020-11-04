package com.caostudy.mapper;

import com.caostudy.my.mapper.MyMapper;
import com.caostudy.pojo.Category;
import com.caostudy.pojo.vo.CategoryVO;
import com.caostudy.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {
    public List<CategoryVO> getSubCatList(Integer rootCatId);


    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap")Map<String,Object> map);

}