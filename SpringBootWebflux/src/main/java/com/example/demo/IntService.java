package com.example.demo;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class IntService {

  public Flux<Integer> getInts() {
    return Flux.just(1, 2, 3, 4, 0, 21).map(x -> 100 / x);
        //.onErrorMap(ex -> new RuntimeException(""));
    //.doOnError(ex -> System.out.println(ex));
  }
}
