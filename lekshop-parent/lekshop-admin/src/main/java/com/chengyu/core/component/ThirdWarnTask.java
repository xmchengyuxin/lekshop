package com.chengyu.core.component;

import com.chengyu.core.service.system.ThirdWarnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @title  接口预警定时器
 * @author LeGreen
 * @date   2021/10/23
 */
@Component
public class ThirdWarnTask {
    private Logger LOGGER =LoggerFactory.getLogger(ThirdWarnTask.class);
    
    @Autowired
    private ThirdWarnService thirdWarnService;

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每30分钟扫描一次
     */
    @Scheduled(cron = "0 0/30 * ? * ?")
    private void cancelTimeOutOrder() throws Exception {
        LOGGER.info("执行第三方接口预警");
        thirdWarnService.warn();
    }
}
