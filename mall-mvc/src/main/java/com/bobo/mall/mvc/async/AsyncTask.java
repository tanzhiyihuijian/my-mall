package com.bobo.mall.mvc.async;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.TimeUnit;

//@Component
public class AsyncTask {

    @Async
    public void sayHello(String name) {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("hello, " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
