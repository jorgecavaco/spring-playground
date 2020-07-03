package com.github.springbootkotlin.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingPongController() {

    @PostMapping("/ping")
    fun aggregate(): String = "pong"
}