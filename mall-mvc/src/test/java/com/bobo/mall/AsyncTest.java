package com.bobo.mall;

import com.bobo.mall.mvc.async.AsyncTask;
import org.junit.Test;

import javax.annotation.Resource;

public class AsyncTest extends BaseTest {

    @Resource
    private AsyncTask asyncTask;


    @Test
    public void f1() {

        System.out.println("begin ..");

        asyncTask.sayHello("小明");

        System.out.println("end ...");

    }

}
