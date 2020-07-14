package com.github.spring_examples2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {

    @Bean("getHelloWorldRepository1")
    public HelloWorld2Repository getHelloWorldRepository(){
        return new HelloWorld2Repository();
    }

    @Bean("helloWorldRepository2")
    public HelloWorld2Repository getHelloWorldRepository2(){
        return new HelloWorld2Repository();
    }

    @Bean

    public HelloWorldService getHelloWorldService(@Qualifier("helloWorldRepository2") HelloWorld2Repository helloWorldRepository) {
        return new HelloWorldService(helloWorldRepository);
    }
}
