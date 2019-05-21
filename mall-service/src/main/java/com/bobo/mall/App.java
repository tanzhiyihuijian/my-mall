package com.bobo.mall;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:spring.xml");
        ioc.start();


        while (true) {

        }
    }
}
