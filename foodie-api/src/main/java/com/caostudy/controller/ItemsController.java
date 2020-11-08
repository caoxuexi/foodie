package com.caostudy.controller;

import com.caostudy.pojo.*;
import com.caostudy.pojo.vo.CommentLevelCountsVO;
import com.caostudy.pojo.vo.ItemInfoVO;
import com.caostudy.pojo.vo.ShopcartVO;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    //用于用户长时间未登录网址，刷新购物车中的数据(主要是商品价格)
    @ApiOperation(value = "根据商品规格ids查找最新的商品数据", notes = "根据商品规格ids查找最新的商品数据", httpMethod = "GET")
    @GetMapping("/refresh")
    public CaoJSONResult refresh(
            @ApiParam(name = "itemSpecIds",value = "拼接的规格ids",example = "1001,1003,1005 ",required = true)
            @RequestParam String itemSpecIds) {
        if(StringUtils.isBlank(itemSpecIds)){
            return CaoJSONResult.ok();
        }
        List<ShopcartVO> list = itemService.queryItemsBySpecIds(itemSpecIds);
        return CaoJSONResult.ok(list);
    }

    //用于用户长时间未登录网址，刷新购物车中的数据(主要是商品价格)
    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public CaoJSONResult del(
            @ApiParam(name = "userId",value = "用户id",example = "1908017YR51G1XWH",required = true)
            @RequestParam String userId,
            @ApiParam(name = "itemSpecId",value = "商品规格id",example = "bingan-1001-spec-1",required = true)
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response) {
        if(StringUtils.isBlank(userId)||StringUtils.isBlank(itemSpecId)){
            return CaoJSONResult.errorMsg("参数不能为空");
        }
        //TODO 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的数据

        return CaoJSONResult.ok();
    }
}
