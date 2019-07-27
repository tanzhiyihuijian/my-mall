package com.bobo.mall.service.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarqueeJob {

    @Scheduled(cron = "0/10 * * * * ? ")
    public void sendMarquee2GameServer() {
        System.out.println("start -->  -->");
    }
}
