package com.example.homework3.service;

import com.example.homework3.entity.RamenAPIResponse;
import com.example.homework3.entity.RamenDBResponse;
import com.example.homework3.entity.ToppingResponse;
import com.example.homework3.entity.UserResponse;
import com.example.homework3.repository.RamenRepository;
import com.example.homework3.repository.ToppingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RamenServiceImplTest {

    @Mock /* mockとして使用するクラス(テスト対象では無いクラス) */
    private RamenRepository ramenRepository;

    @Mock /* mockとして使用するクラス(テスト対象では無いクラス) */
    private ToppingRepository toppingRepository;

    @InjectMocks /* mockを注入されるクラス */
    private RamenServiceImpl ramenService;

    @BeforeEach
    public void initMocks() {
        /* mockの初期化 */
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findの動作を確認() {
        /* mockの動作を定義 */
        when(ramenRepository.find(1)).thenReturn(new RamenDBResponse(1, "ramen1", 100, "place1"));
        when(toppingRepository.findByRamenId(1)).thenReturn(List.of(
                new ToppingResponse(1, "topping1", 100, 1),
                new ToppingResponse(2, "topping2", 200, 1)
        ));

        /* テストの実行 */
        RamenAPIResponse ramenAPIResponse = ramenService.find(1);
        assertEquals(1, ramenAPIResponse.getId());
        assertEquals("ramen1", ramenAPIResponse.getName());
        assertEquals(100, ramenAPIResponse.getPrice());
        assertEquals("place1", ramenAPIResponse.getPlaceName());

        List<ToppingResponse> toppingList = ramenAPIResponse.getToppingList();

        assertEquals(2, toppingList.size());
        assertEquals("topping1", toppingList.get(0).getName());
        assertEquals("topping2", toppingList.get(1).getName());
    }

    @Test
    void findAllの動作を確認() {
        /* mockの動作を定義 */
        when(ramenRepository.findAll()).thenReturn(List.of(
                new RamenDBResponse(1, "ramen1", 100, "place1"),
                new RamenDBResponse(2, "ramen2", 200, "place2")
        ));
        when(toppingRepository.findByRamenId(1)).thenReturn(List.of(
                new ToppingResponse(1, "topping1", 100, 1),
                new ToppingResponse(2, "topping2", 200, 1)
        ));
        when(toppingRepository.findByRamenId(2)).thenReturn(List.of(
                new ToppingResponse(3, "topping3", 300, 2),
                new ToppingResponse(4, "topping4", 400, 2),
                new ToppingResponse(5, "topping5", 500, 2)
        ));

        /* テストの実行 */
        List<RamenAPIResponse> ramenList = ramenService.findAll();
        assertEquals(2, ramenList.size());

        RamenAPIResponse ramenAPIResponse1 = ramenList.get(0);
        assertEquals(1, ramenAPIResponse1.getId());
        assertEquals("ramen1", ramenAPIResponse1.getName());
        assertEquals(100, ramenAPIResponse1.getPrice());
        assertEquals("place1", ramenAPIResponse1.getPlaceName());

        List<ToppingResponse> toppingList1 = ramenAPIResponse1.getToppingList();
        assertEquals(2, toppingList1.size());

        RamenAPIResponse ramenAPIResponse2 = ramenList.get(1);
        assertEquals(2, ramenAPIResponse2.getId());
        assertEquals("ramen2", ramenAPIResponse2.getName());
        assertEquals(200, ramenAPIResponse2.getPrice());
        assertEquals("place2", ramenAPIResponse2.getPlaceName());

        List<ToppingResponse> toppingList2 = ramenAPIResponse2.getToppingList();
        assertEquals(3, toppingList2.size());
    }
}