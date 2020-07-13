package com.github.springapplicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:beans.xml");

        HelloWorld helloWorld = ctx.getBean("helloWorld", HelloWorld.class);
        helloWorld.sayHello();

    }
}
