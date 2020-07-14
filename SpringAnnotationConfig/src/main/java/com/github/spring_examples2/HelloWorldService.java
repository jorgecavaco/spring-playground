package com.github.spring_examples2;

import org.springframework.beans.factory.annotation.Autowired;

public class HelloWorldService {
    private String message;

    private final HelloWorld2Repository helloWorld2Repository;

    @Autowired
    public HelloWorldService(HelloWorld2Repository helloWorld2Repository) {
            this.helloWorld2Repository = helloWorld2Repository;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {

        System.out.println("Your Message : " + helloWorld2Repository.getMessage());
    }

}
