package org.example.puzzle.buysellstock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuySellStockPuzzleTest {
    private BuySellStockPuzzle buySellStockPuzzle;
    int[] priceList;

    @BeforeEach
    void setUp() {
        buySellStockPuzzle = new BuySellStockPuzzle();
        //priceList = new int[]{7, 1, 5, 3, 6, 4};
        priceList = new int[]{7, 6, 4, 3, 1};
    }

    @Test
    void getBuyDay() {
        buySellStockPuzzle = new BuySellStockPuzzle();
        int buyDay = buySellStockPuzzle.getBuyDay(priceList);
        System.out.println("buyDay = " + buyDay);
        assertEquals(4, buyDay);
    }

    @Test
    void getSellDay() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        List<Integer> subList = integers.subList(4, integers.size());
        System.out.println("subList = " + subList);
    }
}