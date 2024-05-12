package com.example.homework3.service;

import com.example.homework3.entity.RamenAPIResponse;
import com.example.homework3.entity.RamenDBResponse;
import com.example.homework3.entity.ToppingResponse;
import com.example.homework3.entity.UserResponse;
import com.example.homework3.repository.RamenRepository;
import com.example.homework3.repository.ToppingRepository;
import com.example.homework3.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RamenServiceImplTest {

    @Mock /* mockとして使用するクラス(テスト対象では無いクラス) */
    private RamenRepository ramenRepository;

    @Mock /* mockとして使用するクラス(テスト対象では無いクラス) */
    private ToppingRepository toppingRepository;

    @InjectMocks /* mockを注入されるクラス */
    private RamenServiceImpl ramenServiceImpl;

    @BeforeEach
    public void initMocks() {
        /* mockの初期化 */
        MockitoAnnotations.openMocks(this);
    }

    ToppingResponse toppingResponse = new ToppingResponse(1,"topping1",1,1);
    ToppingResponse toppingResponse2 = new ToppingResponse(2,"topping2",2,2);
    ToppingResponse toppingResponse3 = new ToppingResponse(3,"topping3",3,3);
    List<ToppingResponse> toppingList = Arrays.asList(toppingResponse,toppingResponse2);
    List<ToppingResponse> toppingList2 = Arrays.asList(toppingResponse,toppingResponse2,toppingResponse3);

    @Test
    void findの動作を確認() {
        /* mockの動作を定義 */
        when(ramenRepository.find(1)).thenReturn(new RamenDBResponse(1, "ramen1", 100, "place1"));
        when(toppingRepository.findByRamenId(1)).thenReturn(toppingList);

        /* テストの実行 */
        RamenAPIResponse ramenAPIResponse = ramenServiceImpl.find(1);
        assertEquals(1, ramenAPIResponse.getId());
        assertEquals("ramen1", ramenAPIResponse.getName());
        assertEquals(100, ramenAPIResponse.getPrice());
        assertEquals("place1", ramenAPIResponse.getPlaceName());
        assertEquals("topping1", ramenAPIResponse.getToppingList().get(0).getName());
        assertEquals("topping2", ramenAPIResponse.getToppingList().get(1).getName());
    }

    @Test
    void findAllテスト() {
        /* mockの動作を定義 */
        when(ramenRepository.find(1)).thenReturn(new RamenDBResponse(1, "ramen1", 100, "place1"));
        when(toppingRepository.findByRamenId(1)).thenReturn(toppingList);
        when(ramenRepository.find(2)).thenReturn(new RamenDBResponse(2, "ramen2", 200, "place2"));
        when(toppingRepository.findByRamenId(2)).thenReturn(toppingList2);

        /* テストの実行 */
        RamenAPIResponse ramenAPIResponse = ramenServiceImpl.find(1);
        assertEquals(1, ramenAPIResponse.getId());
        assertEquals("ramen1", ramenAPIResponse.getName());
        assertEquals(100, ramenAPIResponse.getPrice());
        assertEquals("place1", ramenAPIResponse.getPlaceName());
        assertEquals("topping1", ramenAPIResponse.getToppingList().get(0).getName());
        assertEquals("topping2", ramenAPIResponse.getToppingList().get(1).getName());
        RamenAPIResponse ramenAPIResponse2 = ramenServiceImpl.find(2);
        assertEquals(2, ramenAPIResponse2.getId());
        assertEquals("ramen2", ramenAPIResponse2.getName());
        assertEquals(200, ramenAPIResponse2.getPrice());
        assertEquals("place2", ramenAPIResponse2.getPlaceName());
        assertEquals("topping1", ramenAPIResponse2.getToppingList().get(0).getName());
        assertEquals("topping2", ramenAPIResponse2.getToppingList().get(1).getName());
        assertEquals("topping3", ramenAPIResponse2.getToppingList().get(2).getName());
    }


}