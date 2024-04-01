package com.example.homework3.controller;

import com.example.homework3.Homework3Application;
import com.example.homework3.entity.RamenAPIResponse;
import com.example.homework3.entity.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(classes = Homework3Application.class)
class RamenControllerTest {

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
    void findAll実行時ラーメンが4つ取得される() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/ramen/findAll"))
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString(); // レスポンスの中からボディ部分を取得
        RamenAPIResponse[] ramenAPIResponse = objectMapper.readValue(body, RamenAPIResponse[].class); // ボディ部分をUserResponseの配列に変換
        assertEquals(4, ramenAPIResponse.length);
    }

    @Test
    void findAll実行時1つ目のラーメンのトッピングがNori50円とTamago100円() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/ramen/findAll"))
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        RamenAPIResponse[] ramenAPIResponse = objectMapper.readValue(body, RamenAPIResponse[].class); // ボディ部分をUserResponseの配列に変換
        RamenAPIResponse firstRamen = ramenAPIResponse[0];
        assertEquals("Nori", firstRamen.getToppingList().get(0).getName());
        assertEquals(50, firstRamen.getToppingList().get(0).getPrice());
        assertEquals("Tamago", firstRamen.getToppingList().get(1).getName());
        assertEquals(100, firstRamen.getToppingList().get(1).getPrice());
    }

    @Test
    void findAll実行時1つ目と2つ目のラーメンのトッピング数が異なる() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/ramen/findAll"))
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        RamenAPIResponse[] ramenAPIResponse = objectMapper.readValue(body, RamenAPIResponse[].class); // ボディ部分をUserResponseの配列に変換
        RamenAPIResponse firstRamen = ramenAPIResponse[0];
        RamenAPIResponse secondRamen = ramenAPIResponse[1];
        assertNotEquals(firstRamen.getToppingList().size(), secondRamen.getToppingList().size());
    }
}