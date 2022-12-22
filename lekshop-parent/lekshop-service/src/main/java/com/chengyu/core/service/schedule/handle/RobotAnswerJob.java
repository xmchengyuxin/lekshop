package com.chengyu.core.service.schedule.handle;

import com.chengyu.core.service.schedule.RedisDelayQueueHandle;
import com.chengyu.core.util.netty.NettyPushUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  自动回复
 * @author LeGreen
 * @date   2022/6/2
 */
@Slf4j
@Component
public class RobotAnswerJob implements RedisDelayQueueHandle<String> {

    @Autowired
    private NettyPushUtil nettyPushUtil;

    @Override
    public void execute(String data) {
        log.info("机器人：{}",data);
        //执行业务操作
        try {
            nettyPushUtil.sendMsg(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
