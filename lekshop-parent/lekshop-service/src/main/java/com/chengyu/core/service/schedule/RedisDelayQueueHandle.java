package com.chengyu.core.service.schedule;

public interface RedisDelayQueueHandle <T>{
    void execute(T t);

    void execute(String data);
}
