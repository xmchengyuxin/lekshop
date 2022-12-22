package com.chengyu.core.service.schedule.handle;

import com.chengyu.core.service.order.OrderCommentService;
import com.chengyu.core.service.schedule.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  自动好评
 * @author LeGreen
 * @date   2022/5/5
 */
@Slf4j
@Component
public class OrderAutoCommentJob implements RedisDelayQueueHandle<String> {

    @Autowired
    private OrderCommentService orderCommentService;

    @Override
    public void execute(String data) {
        log.info("自动好评：{}",data);
        //执行业务操作
        try {
            orderCommentService.autoComment(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
