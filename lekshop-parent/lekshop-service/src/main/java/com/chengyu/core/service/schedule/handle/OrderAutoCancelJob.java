package com.chengyu.core.service.schedule.handle;

import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.schedule.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  订单超时未支付自动取消
 * @author LeGreen
 * @date   2022/5/5
 */
@Slf4j
@Component
public class OrderAutoCancelJob implements RedisDelayQueueHandle<String> {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(String data) {
        log.info("订单超时未支付自动取消：{}",data);
        //执行业务操作
        try {
            orderService.autoCancelOrder(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
