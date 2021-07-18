package com.caostudy.config;

import com.caostudy.service.OrderService;
import com.caostudy.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Cao Study
 * @description OrderJob
 * @date 2021/7/6 20:01
 */
@Component
public class OrderJob {
    @Autowired
    private OrderService orderService;

    //一小时执行一次
    @Scheduled(cron="0 0 0/1 * * ? ")
    public void autoCloseOrder(){
        orderService.closeOrder();
        System.out.println("执行定时任务，当前时间为:"
                + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
    }
}
