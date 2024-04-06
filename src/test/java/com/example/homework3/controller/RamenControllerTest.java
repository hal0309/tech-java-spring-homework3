package com.example.homework3.controller;

import com.example.homework3.Homework3Application;
import com.example.homework3.entity.RamenAPIResponse;
import com.example.homework3.entity.RamenDBResponse;
import com.example.homework3.entity.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = Homework3Application.class)
public class RamenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAll実行時のHTTPステータスが200() throws Exception {
        this.mockMvc.perform(get("/ramen/findAll"))
                .andDo(print())  // 取得結果を画面に出力
                .andExpect(status().isOk()); // ステータスコードが200であることを検証

    }

    @Test
    void findAll4つのラーメンが取得できる200() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/ramen/findAll"))
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString(); // レスポンスの中からボディ部分を取得
        RamenAPIResponse[] ramenAPIResponse = objectMapper.readValue(body, RamenAPIResponse[].class); // ボディ部分をRamenDBResponseの配列に変換
        assertEquals(4, ramenAPIResponse.length); // レスポンスに含まれるユーザーが3人であることを検証
    }

    @Test
    void findAll1つのラーメンに対して2つのトッピングが取得でき200() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/ramen/findAll"))
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString(); // レスポンスの中からボディ部分を取得
        RamenAPIResponse[] ramenAPIResponse = objectMapper.readValue(body, RamenAPIResponse[].class); // ボディ部分をRamenDBResponseの配列に変換
        RamenAPIResponse ramenAPIResponse1 = ramenAPIResponse[0]; // 1人目のユーザーを取得
        assertEquals("Nori", ramenAPIResponse1.getToppingList().get(0).getName());
        assertEquals("Tamago", ramenAPIResponse1.getToppingList().get(1).getName());
        assertEquals(50, ramenAPIResponse1.getToppingList().get(0).getPrice());
        assertEquals(100, ramenAPIResponse1.getToppingList().get(1).getPrice());
    }

    @Test
    void findAll1つ目のラーメンのトッピング数と2つ目のラーメンのトッピング数が異なる200() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/ramen/findAll"))
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString(); // レスポンスの中からボディ部分を取得
        RamenAPIResponse[] ramenAPIResponse = objectMapper.readValue(body, RamenAPIResponse[].class); // ボディ部分をRamenDBResponseの配列に変換
        RamenAPIResponse ramenAPIResponse1 = ramenAPIResponse[0]; // 1人目のユーザーを取得
        RamenAPIResponse ramenAPIResponse2 = ramenAPIResponse[1]; // 2人目のユーザーを取得
        assertNotSame(ramenAPIResponse1.getToppingList().size(), ramenAPIResponse2.getToppingList().size());
    }
}
