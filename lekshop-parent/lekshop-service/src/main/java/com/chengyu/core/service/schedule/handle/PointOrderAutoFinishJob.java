package com.chengyu.core.service.schedule.handle;

import com.chengyu.core.service.point.PointOrderService;
import com.chengyu.core.service.schedule.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PointOrderAutoFinishJob implements RedisDelayQueueHandle<String> {

    @Autowired
    private PointOrderService orderService;

    @Override
    public void execute(String data) {
        log.info("积分商城订单自动确认收货：{}",data);
        //执行业务操作
        try {
            orderService.autoFinishOrder(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
