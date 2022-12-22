package com.chengyu.core.service.schedule.handle;

import com.chengyu.core.service.order.OrderRefundService;
import com.chengyu.core.service.schedule.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  卖家超时未确认收货自动确认
 * @author LeGreen
 * @date   2022/5/6
 */
@Slf4j
@Component
public class RefundAutoConfirmJob implements RedisDelayQueueHandle<String> {

    @Autowired
    private OrderRefundService orderRefundService;

    @Override
    public void execute(String data) {
        log.info("卖家超时未确认收货自动确认：{}",data);
        //执行业务操作
        try {
            orderRefundService.autoConfirmRefund(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
