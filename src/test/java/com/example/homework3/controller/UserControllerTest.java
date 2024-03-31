package com.example.homework3.controller;

import com.example.homework3.Homework3Application;
import com.example.homework3.entity.UserResponse;
import com.example.homework3.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@AutoConfigureMockMvc
@SpringBootTest(classes = Homework3Application.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAllのstatusが200() throws Exception {
        this.mockMvc.perform(get("/user/findAll"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findAllを呼び出すとbodyに2つのデータがある() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/user/findAll"))
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        UserResponse[] userResponses = objectMapper.readValue(body, UserResponse[].class);
        Arrays.stream(userResponses).forEach(System.out::println);

        assertEquals(2, userResponses.length);
    }


}