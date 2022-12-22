package com.chengyu.core.service.schedule.handle;

import com.chengyu.core.service.order.OrderGroupService;
import com.chengyu.core.service.schedule.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  拼团失败自动取消
 * @author LeGreen
 * @date   2022/5/25
 */
@Slf4j
@Component
public class OrderGroupAutoCancelJob implements RedisDelayQueueHandle<String> {

    @Autowired
    private OrderGroupService orderGroupService;

    @Override
    public void execute(String data) {
        log.info("拼团失败自动取消：{}",data);
        //执行业务操作
        try {
            orderGroupService.autoCancel(Integer.parseInt(data));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
