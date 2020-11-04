package com.caostudy.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 曹学习
 * @description CategoryVo 二级分类
 * @date 2020/11/4 17:28
 */
@Getter
@Setter
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private String fatherId;

    //三级分类的Vo
    private List<SubCategoryVO> subCatList;
}
