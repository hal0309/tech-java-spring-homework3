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
    void findAll実行時のHTTPステータスが200() throws Exception {
        this.mockMvc.perform(get("/user/findAll"))
                .andDo(print())  // 取得結果を画面に出力
                .andExpect(status().isOk()); // ステータスコードが200であることを検証
    }

    @Test
    void findAll実行時ユーザーが3人取得される() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/user/findAll"))
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString(); // レスポンスの中からボディ部分を取得
        UserResponse[] userResponses = objectMapper.readValue(body, UserResponse[].class); // ボディ部分をUserResponseの配列に変換
        assertEquals(3, userResponses.length); // レスポンスに含まれるユーザーが3人であることを検証
    }

    @Test
    void findAll実行時1人目のユーザーの出身地がYokohamaで好きなラーメンがIekei() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/user/findAll"))
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        UserResponse[] userResponses = objectMapper.readValue(body, UserResponse[].class);
        UserResponse firstUser = userResponses[0]; // 1人目のユーザーを取得
        assertEquals("Yokohama", firstUser.getLiveInCityName()); // 1人目のユーザーの出身地がYokohamaであることを検証
        assertEquals("Iekei", firstUser.getFavoriteRamenName()); // 1人目のユーザーの好きなラーメンがIekeiであることを検証

    }
}