package com.github.springbootkotlin

import com.github.springbootkotlin.controller.PingPongController
import org.assertj.core.api.Assertions
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [PingPongController::class])
class PingPongControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testAggregationController() {
        val result = mockMvc
                .perform(
                        post(
                                "/ping"
                        )
                )
                .andExpect {
                    status().isOk
                }.andReturn()

        Assertions.assertThat(result.response.contentAsString).isEqualTo("pong")
    }
}