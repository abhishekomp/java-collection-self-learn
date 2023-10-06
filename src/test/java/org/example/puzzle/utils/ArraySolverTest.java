package org.example.puzzle.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArraySolverTest {

    ArraySolver arraySolver;

    @BeforeEach
    void setUp() {
        arraySolver = new ArraySolver();
    }

    @Test
    void isDuplicatePresent() {
    }

    @Test
    void test_convert_int_array_to_list_passes_when_returned_list_matches_expected_list() {
        int[] inputArr = new int[] {7, 1, 3, 9, 2, 4, 8, 5, 6};
        List<Integer> expectedList = List.of(7, 1, 3, 9, 2, 4, 8, 5, 6);
        List<Integer> actualIntegerList = arraySolver.convertIntArrayToList(inputArr);
        //assertTrue(expectedList.size(), actualIntegerList.size(), "Sizes are same");
        assertIterableEquals(expectedList, actualIntegerList);
    }

    //To do write test case for negative situation
//    @Test
//    void test_convert_int_array_to_list_passes_when_returned_list_does_not_matches_expected_list() {
//        int[] inputArr = new int[] {7, 1, 3, 9, 2, 4, 8, 5, 6};
//        List<Integer> expectedList = List.of(7, 1, 3, 9, 2, 4, 8, 5);
//        List<Integer> actualIntegerList = arraySolver.convertIntArrayToList(inputArr);
//        assertIterableEquals(expectedList, actualIntegerList);
//    }
}