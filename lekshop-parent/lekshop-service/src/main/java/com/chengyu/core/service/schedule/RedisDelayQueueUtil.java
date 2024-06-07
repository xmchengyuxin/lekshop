package com.chengyu.core.service.schedule;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisDelayQueueUtil {

    @Autowired
    private RedissonClient redissonClient;

    public <T> boolean addDelayQueue(T value, Date delayTime, String queueCode) {
        Date now = DateUtil.date();
        if(delayTime.before(now)) {
            return false;
        }
        return addDelayQueue(value, DateUtil.between(delayTime, now, DateUnit.SECOND, true), TimeUnit.SECONDS, queueCode);
    }

    /**
     * 添加延迟队列
     *
     * @param value:队列值
     * @param delay:延迟时间
     * @param timeUnit:时间单位
     * @param queueCode:队列键
     * @param <T>
     */
    public <T> boolean addDelayQueue(T value, long delay, TimeUnit timeUnit, String queueCode) {
        if (StringUtils.isBlank(queueCode) || Objects.isNull(value)) {
            return false;
        }
        try {
            // redission的阻塞队列
            RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque(queueCode);
            // redission的延时队列
            RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
            // 延时队列添加数据
            delayedQueue.offer(value, delay, timeUnit);
            //delayedQueue.destroy();
            log.info("添加延时队列成功，队列键：{}，队列值：{}，延迟时间：{}", queueCode, value, timeUnit.toSeconds(delay) + "秒");
        } catch (Exception e) {
            log.error("添加延时队列失败： {}", e.getMessage());
            throw new RuntimeException("(添加延时队列失败)");
        }
        return true;
    }

    /**
     * 获取延迟队列
     *
     * @param queueCode
     * @param <T>
     */
    public <T> T getDelayQueue(@NonNull String queueCode) throws InterruptedException {
        if (StringUtils.isBlank(queueCode)) {
            return null;
        }
        RBlockingDeque<Map> blockingDeque = redissonClient.getBlockingDeque(queueCode);
        // 将队列中放入的第一个元素取出
        T value = (T) blockingDeque.poll();
        return value;
    }
    /**
     * 删除指定队列中的消息
     * @param o 指定删除的消息对象队列值(同队列需保证唯一性)
     * @param queueCode 指定队列键
     */
    public boolean removeDelayedQueue(Object o,String queueCode) {
        if (StringUtils.isBlank(queueCode) || Objects.isNull(o)) {
            return false;
        }
        RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque(queueCode);
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        boolean flag = delayedQueue.remove(o);
        //delayedQueue.destroy();
        if(flag){
            log.info("删除延时队列成功， 删除信息:{}，延迟时间：{}", o,queueCode);
        }
        return flag;
    }
}
