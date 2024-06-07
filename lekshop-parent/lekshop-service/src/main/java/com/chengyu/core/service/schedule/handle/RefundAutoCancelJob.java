package com.chengyu.core.service.schedule.handle;

import com.chengyu.core.service.order.OrderRefundService;
import com.chengyu.core.service.schedule.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  买家超时未寄货自动取消售后申请
 * @author LeGreen
 * @date   2022/5/6
 */
@Slf4j
@Component
public class RefundAutoCancelJob implements RedisDelayQueueHandle<String> {

    @Autowired
    private OrderRefundService orderRefundService;

    @Override
    public void execute(String data) {
        log.info("买家超时未寄货自动取消售后申请：{}",data);
        //执行业务操作
        try {
            orderRefundService.autoCancelRefund(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
