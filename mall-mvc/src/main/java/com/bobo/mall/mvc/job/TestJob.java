package com.bobo.mall.mvc.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {

    @Scheduled(cron = "0/10 * * * * ? ")
    public void f1() {
        System.out.println("-- -- =>");
    }

}
