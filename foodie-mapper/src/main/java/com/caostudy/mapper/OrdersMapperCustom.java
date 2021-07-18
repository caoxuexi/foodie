package com.caostudy.mapper;

import com.caostudy.pojo.OrderStatus;
import com.caostudy.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Cao Study
 */
public interface OrdersMapperCustom {

    public List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);

    public int getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    public List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);

}