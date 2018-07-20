package com.baidu.mybaidu.schedule;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTest {

    private static final Logger logger = Logger.getLogger(ScheduleTest.class);
    //@Scheduled(cron = "0/2 * * * * ?")
    public void work(){
        logger.fatal("testfatal");
        logger.warn("testwarn");

    }
}
