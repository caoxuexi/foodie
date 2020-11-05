package com.caostudy.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 曹学习
 * @description CommentLevelCountsVO 用于展示商品评价数量
 * @date 2020/11/5 17:51
 */
@Getter
@Setter
public class CommentLevelCountsVO {
    public Integer totalCounts;
    public Integer goodCounts;
    public Integer normalCounts;
    public Integer badCounts;
}
