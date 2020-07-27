package com.example.demo;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

  @Override
  public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
    DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();

    DataBuffer dataBuffer = bufferFactory.wrap("Global Error Handler".getBytes());
    return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
  }
}
