package com.caostudy.service.impl;

import com.caostudy.enums.CommentLevel;
import com.caostudy.mapper.*;
import com.caostudy.pojo.*;
import com.caostudy.pojo.vo.CommentLevelCountsVO;
import com.caostudy.pojo.vo.ItemCommentVO;
import com.caostudy.pojo.vo.SearchItemsVO;
import com.caostudy.service.ItemService;
import com.caostudy.utils.DesensitizationUtil;
import com.caostudy.utils.PagedGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @description CarouselServiceImpl
 * @date 2020/11/4 11:32
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;
    @Autowired
    private ItemsMapperCustom itemsMapperCostume;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example itemsImgExp=new Example(ItemsImg.class);
        Example.Criteria criteria=itemsImgExp.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsImgMapper.selectByExample(itemsImgExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example itemsSpecExp=new Example(ItemsSpec.class);
        Example.Criteria criteria= itemsSpecExp.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsSpecMapper.selectByExample(itemsSpecExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example itemsParamExp=new Example(ItemsParam.class);
        Example.Criteria criteria= itemsParamExp.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsParamMapper.selectOneByExample(itemsParamExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts=getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCounts=getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts=getCommentCounts(itemId, CommentLevel.BAD.type);
        Integer totalCounts=goodCounts+normalCounts+badCounts;

        CommentLevelCountsVO countsVO=new CommentLevelCountsVO();
        countsVO.setTotalCounts(totalCounts);
        countsVO.setBadCounts(badCounts);
        countsVO.setNormalCounts(normalCounts);
        countsVO.setGoodCounts(goodCounts);
        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId,Integer level){
        ItemsComments condition=new ItemsComments();
        condition.setItemId(itemId);
        if(level!=null){
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(
            String itemId, Integer level,
            Integer page, Integer pageSize) {
        Map<String,Object> map=new HashMap<>();
        map.put("itemId",itemId);
        map.put("level",level);
        PageHelper.startPage(page,pageSize);
        List<ItemCommentVO> list = itemsMapperCostume.queryItemComments(map);
        for (ItemCommentVO vo:list) {
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }
        return setterPagedGrid(list,page);
    }

    private PagedGridResult setterPagedGrid(List<?> list,Integer page){
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize) {
        Map<String,Object> map=new HashMap<>();
        map.put("keywords",keywords);
        map.put("sort",sort);
        PageHelper.startPage(page,pageSize);
        List<SearchItemsVO> list = itemsMapperCostume.searchItems(map);
        return setterPagedGrid(list,page);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize) {
        Map<String,Object> map=new HashMap<>();
        map.put("catId",catId);
        map.put("sort",sort);
        PageHelper.startPage(page,pageSize);
        List<SearchItemsVO> list = itemsMapperCostume.searchItemsByThirdCat(map);
        return setterPagedGrid(list,page);
    }
}
