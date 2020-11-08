package com.caostudy.service;

import com.caostudy.pojo.Items;
import com.caostudy.pojo.ItemsImg;
import com.caostudy.pojo.ItemsParam;
import com.caostudy.pojo.ItemsSpec;
import com.caostudy.pojo.vo.CommentLevelCountsVO;
import com.caostudy.pojo.vo.ItemCommentVO;
import com.caostudy.pojo.vo.ShopcartVO;
import com.caostudy.utils.PagedGridResult;

import java.util.List;

/**
 * @author 曹学习
 * @description CarouselService
 * @date 2020/11/4 11:30
 */
public interface ItemService {
    /**
     * 根据商品id查询详情
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品id查询商品的评价等级数量
     * @param itemId
     */
    public CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id查询商品的评价 （分页）
     */
    public PagedGridResult queryPagedComments(
            String itemId,Integer level,
            Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     */
    public PagedGridResult searchItems(
            String keywords,String sort,
            Integer page, Integer pageSize);

    /**
     * 根据分类id搜索商品列表
     */
    public PagedGridResult searchItems(
            Integer catId,String sort,
            Integer page, Integer pageSize);

    /**
     * 根据规格ids查询最新的购物车中商品数据
     */
    public List<ShopcartVO> queryItemsBySpecIds(String specIds);
}
