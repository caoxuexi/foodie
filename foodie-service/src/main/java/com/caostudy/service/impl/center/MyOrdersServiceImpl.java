package com.caostudy.service.impl.center;

import com.caostudy.mapper.OrderStatusMapper;
import com.caostudy.mapper.OrdersMapper;
import com.caostudy.mapper.OrdersMapperCustom;
import com.caostudy.pojo.Orders;
import com.caostudy.pojo.vo.MyOrdersVO;
import com.caostudy.pojo.vo.OrderStatusCountsVO;
import com.caostudy.service.center.MyOrdersService;
import com.caostudy.utils.PagedGridResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cao Study
 * @description MyOrdersServiceImpl
 * @date 2021/7/18 16:42
 */
@Service
public class MyOrdersServiceImpl extends BaseService implements MyOrdersService {
    @Autowired
    public OrdersMapperCustom ordersMapperCustom;

    @Autowired
    public OrdersMapper ordersMapper;

    @Autowired
    public OrderStatusMapper orderStatusMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyOrders(String userId,
                                         Integer orderStatus,
                                         Integer page,
                                         Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }

        PageHelper.startPage(page, pageSize);

        List<MyOrdersVO> list = ordersMapperCustom.queryMyOrders(map);

        return setterPagedGrid(list, page);
    }

    @Override
    public void updateDeliverOrderStatus(String orderId) {

    }

    @Override
    public Orders queryMyOrder(String userId, String orderId) {
        return null;
    }

    @Override
    public boolean updateReceiveOrderStatus(String orderId) {
        return false;
    }

    @Override
    public boolean deleteOrder(String userId, String orderId) {
        return false;
    }

    @Override
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {
        return null;
    }

    @Override
    public PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize) {
        return null;
    }
}
