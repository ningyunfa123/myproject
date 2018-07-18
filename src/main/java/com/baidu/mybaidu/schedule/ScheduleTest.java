package com.baidu.mybaidu.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTest {
    @Scheduled(cron = "0/2 * * * * ?")
    public void work(){


        System.out.println("test");
    }
}
