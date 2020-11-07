package com.caostudy.controller;

import com.caostudy.pojo.*;
import com.caostudy.pojo.vo.CommentLevelCountsVO;
import com.caostudy.pojo.vo.ItemInfoVO;
import com.caostudy.service.ItemService;
import com.caostudy.utils.CaoJSONResult;
import com.caostudy.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 曹学习
 * @description HelloController
 * @date 2020/11/1 10:22
 */
@Api(value = "商品接口", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public CaoJSONResult info(
            @ApiParam(name = "itemId",value = "商品id",example = "1",required = true)
            @PathVariable String itemId) {
        if(StringUtils.isBlank(itemId)){
            return CaoJSONResult.errorMsg(null);
        }
        Items item=itemService.queryItemById(itemId);
        List<ItemsImg> itemImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParams = itemService.queryItemParam(itemId);
        ItemInfoVO itemInfoVO=new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemSpecList(itemSpecList);
        itemInfoVO.setItemParams(itemsParams);
        return CaoJSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价的等级", notes = "查询商品评价的等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public CaoJSONResult commentLevel(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId) {
        if(StringUtils.isBlank(itemId)){
            return CaoJSONResult.errorMsg(null);
        }
        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);
        return CaoJSONResult.ok(countsVO);
    }

    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    @GetMapping("/comments")
    public CaoJSONResult comments(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level",value = "评价等级",example = "1",required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page",value = "查询下一页的第几页",example = "2",required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "一页显示的条数",example = "10",required = false)
            @RequestParam Integer pageSize) {
        if(StringUtils.isBlank(itemId)){
            return CaoJSONResult.errorMsg(null);
        }
        if(page ==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=COMMENT_PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.queryPagedComments(
                itemId, level, page, pageSize);
        return CaoJSONResult.ok(pagedGridResult);
    }

    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "GET")
    @GetMapping("/search")
    public CaoJSONResult search(
            @ApiParam(name = "keywords",value = "搜索关键字",required = true)
            @RequestParam String keywords,
            @ApiParam(name = "sort",value = "排序类型",required = false)
            @RequestParam String sort,
            @ApiParam(name = "page",value = "查询第几页",example = "2",required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "一页显示的条数",example = "20",required = false)
            @RequestParam Integer pageSize) {
        if(StringUtils.isBlank(keywords)){
            return CaoJSONResult.errorMsg(null);
        }
        if(page ==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.searchItems(
               keywords, sort, page, pageSize);
        return CaoJSONResult.ok(pagedGridResult);
    }

    @ApiOperation(value = "通过分类id搜索商品列表", notes = "通过分类id搜索商品列表", httpMethod = "GET")
    @GetMapping("/catItems")
    public CaoJSONResult catItems(
            @ApiParam(name = "catId",value = "三级分类id",example = "51",required = true)
            @RequestParam Integer catId,
            @ApiParam(name = "sort",value = "排序类型",required = false)
            @RequestParam String sort,
            @ApiParam(name = "page",value = "查询下一页的第几页",example = "1",required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "一页显示的条数",example = "20",required = false)
            @RequestParam Integer pageSize) {
        if(catId==null){
            return CaoJSONResult.errorMsg(null);
        }
        if(page ==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.searchItems(
                catId, sort, page, pageSize);
        return CaoJSONResult.ok(pagedGridResult);
    }
}
