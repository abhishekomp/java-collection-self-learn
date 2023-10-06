package org.example.puzzle.buysellstock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static org.junit.jupiter.api.Assertions.*;

class BuySellStockPuzzleTest {
    private BuySellStockPuzzle buySellStockPuzzle;
    int[] priceList;

    @BeforeEach
    void setUp() {
        buySellStockPuzzle = new BuySellStockPuzzle();
        priceList = new int[]{7, 1, 5, 3, 6, 4};
        //priceList = new int[]{7, 6, 4, 3, 1};
    }

    @Test
    void getBuyDay() {
        buySellStockPuzzle = new BuySellStockPuzzle();
        int buyDay = buySellStockPuzzle.getBuyDay(priceList);
        System.out.println("buyDay = " + buyDay);
        assertEquals(2, buyDay);
    }

    @Test
    void getSellDay() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        List<Integer> subList = integers.subList(4, integers.size());
        System.out.println("subList = " + subList);
    }

    @Test
    void test_should_get_the_minimum_integer_from_the_integer_list() {
        List<Integer> integerList = List.of(1, 2, 3, 4, 5);
        //Method 1:
        int minVal = Collections.min(integerList);
        System.out.println("minVal = " + minVal);
        assertEquals(1, minVal);

        //Another way:
        Optional<Integer> minValue = integerList.stream().min(comparing(Function.identity()));
        System.out.println("minVal using stream = " + minValue);
        assertEquals(1, minValue.get());
    }
}