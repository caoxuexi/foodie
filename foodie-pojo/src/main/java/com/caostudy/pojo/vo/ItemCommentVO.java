package com.caostudy.pojo.vo;

import com.caostudy.pojo.Items;
import com.caostudy.pojo.ItemsImg;
import com.caostudy.pojo.ItemsParam;
import com.caostudy.pojo.ItemsSpec;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author 曹学习
 * @description 用于展示商品评价的VO
 * @date 2020/11/4 23:41
 */
@Getter
@Setter
public class ItemCommentVO {
    private Integer commentLevel;
    private String content;
    private String specName;
    private Date createdTime;
    private String userFace;
    private String nickname;
}
