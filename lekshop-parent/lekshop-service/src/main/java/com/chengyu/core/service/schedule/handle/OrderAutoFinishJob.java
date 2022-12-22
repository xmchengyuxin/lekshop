package com.chengyu.core.service.schedule.handle;

import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.schedule.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  订单自动确认收货
 * @author LeGreen
 * @date   2022/5/5
 */
@Slf4j
@Component
public class OrderAutoFinishJob implements RedisDelayQueueHandle<String> {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(String data) {
        log.info("订单自动确认收货：{}",data);
        //执行业务操作
        try {
            orderService.autoFinishOrder(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
