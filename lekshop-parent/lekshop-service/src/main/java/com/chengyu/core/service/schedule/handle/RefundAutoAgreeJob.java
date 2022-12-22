package com.chengyu.core.service.schedule.handle;

import com.chengyu.core.service.order.OrderRefundService;
import com.chengyu.core.service.schedule.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  售后申请超时未处理自动同意
 * @author LeGreen
 * @date   2022/5/6
 */
@Slf4j
@Component
public class RefundAutoAgreeJob implements RedisDelayQueueHandle<String> {

    @Autowired
    private OrderRefundService orderRefundService;

    @Override
    public void execute(String data) {
        log.info("售后申请超时未处理自动同意：{}",data);
        //执行业务操作
        try {
            orderRefundService.autoAgreeRefund(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
