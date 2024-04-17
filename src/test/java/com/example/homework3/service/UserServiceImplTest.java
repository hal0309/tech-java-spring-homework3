package com.example.homework3.service;

import com.example.homework3.entity.UserResponse;
import com.example.homework3.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock /* mockとして使用するクラス(テスト対象では無いクラス) */
    private UserRepository userRepository;

    @InjectMocks /* mockを注入されるクラス */
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void initMocks() {
        /* mockの初期化 */
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findの動作を確認() {
        /* mockの動作を定義 */
        when(userRepository.find(1)).thenReturn(new UserResponse(1, "person1", 20, "ramen1", "city1"));

        /* テストの実行 */
        UserResponse userResponse = userServiceImpl.find(1);
        assertEquals(1, userResponse.getId());
        assertEquals("person1", userResponse.getName());
        assertEquals(20, userResponse.getAge());
        assertEquals("ramen1", userResponse.getFavoriteRamenName());
        assertEquals("city1", userResponse.getLiveInCityName());
    }

    @Test
    void findAllの動作を確認_ユーザ数1() {
        /* mockの動作を定義 */
        when(userRepository.findAll()).thenReturn(List.of(new UserResponse(1, "person1", 20, "ramen1", "city1")));

        /* テストの実行 */
        var userResponseList = userServiceImpl.findAll();
        assertEquals(1, userResponseList.size());
        assertEquals(1, userResponseList.get(0).getId());
        assertEquals("person1", userResponseList.get(0).getName());
        assertEquals(20, userResponseList.get(0).getAge());
        assertEquals("ramen1", userResponseList.get(0).getFavoriteRamenName());
        assertEquals("city1", userResponseList.get(0).getLiveInCityName());
    }


    @Test
    void findAllの動作を確認_ユーザ数2() {
        /* mockの動作を定義 */
        when(userRepository.findAll()).thenReturn(List.of(new UserResponse(1, "person1", 20, "ramen1", "city1"), new UserResponse(2, "person2", 30, "ramen2", "city2")));

        /* テストの実行 */
        var userResponseList = userServiceImpl.findAll();
        assertEquals(2, userResponseList.size());
        assertEquals(1, userResponseList.get(0).getId());
        assertEquals("person1", userResponseList.get(0).getName());
        assertEquals(20, userResponseList.get(0).getAge());
        assertEquals("ramen1", userResponseList.get(0).getFavoriteRamenName());
        assertEquals("city1", userResponseList.get(0).getLiveInCityName());
        assertEquals(2, userResponseList.get(1).getId());
        assertEquals("person2", userResponseList.get(1).getName());
        assertEquals(30, userResponseList.get(1).getAge());
        assertEquals("ramen2", userResponseList.get(1).getFavoriteRamenName());
        assertEquals("city2", userResponseList.get(1).getLiveInCityName());
    }
}