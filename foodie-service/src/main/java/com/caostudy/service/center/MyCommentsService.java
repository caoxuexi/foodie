package com.caostudy.service.center;

import com.caostudy.pojo.OrderItems;
import com.caostudy.pojo.bo.OrderItemsCommentBO;
import com.caostudy.utils.PagedGridResult;

import java.util.List;

/**
 * @author Cao Study
 * @description MyOrdersService
 * @date 2021/7/18 16:41
 */
public interface MyCommentsService {
    /**
     * 根据订单id查询关联的商品
     * @param orderId
     * @return
     */
    public List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存用户的评论
     * @param orderId
     * @param userId
     * @param commentList
     */
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);


    /**
     * 我的评价查询 分页
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);

}
