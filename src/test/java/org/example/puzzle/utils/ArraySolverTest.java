package org.example.puzzle.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

    @Test
    void generateIntArray() {
        int finalValue = 10;
        int[] ints = arraySolver.generateIntArray(finalValue);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    void generateArrayOfRandomInt() {
        int length = 5;
        int maxValue  = 10;
        int[] ints = arraySolver.generateArrayOfRandomInt(length, maxValue);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    @Test
    void test_getTheFirstDuplicateElement_should_return_first_duplicate_int_in_the_int_array() {
        int theFirstDuplicateElement = arraySolver.getTheFirstDuplicateElement(new int[]{1, 2, 3, 5, 3});
        System.out.println("theFirstDuplicateElement = " + theFirstDuplicateElement);
        assertEquals(3, theFirstDuplicateElement);
    }

    @Test
    void test_getTheFirstDuplicateElement_should_return_max_int_value_when_no_repeating_value_in_array() {
        int theFirstDuplicateElement = arraySolver.getTheFirstDuplicateElement(new int[]{1, 2, 3, 4, 5});
        System.out.println("theFirstDuplicateElement = " + theFirstDuplicateElement);
        assertEquals(-2147483648, theFirstDuplicateElement);
    }

    @Test
    void test_getIndexOfFirstRepeatingElement_should_return_correct_index_of_first_duplicate_elemment_in_array() {
        int[] inputArr = new int[] {7, 1, 3, 2, 1, 9};
        int indexOfFirstRepeatingElement = arraySolver.getIndexOfFirstRepeatingElement(inputArr);
        System.out.println("indexOfFirstRepeatingElement = " + indexOfFirstRepeatingElement);
        assertEquals(1, indexOfFirstRepeatingElement);
    }

    @Test
    void test_getIndexOfFirstRepeatingElement_should_return_minus_one_when_no_duplicate_element_in_array() {
        int[] inputArr = new int[] {7, 5, 3, 2, 1, 9};
        int indexOfFirstRepeatingElement = arraySolver.getIndexOfFirstRepeatingElement(inputArr);
        System.out.println("indexOfFirstRepeatingElement = " + indexOfFirstRepeatingElement);
        assertEquals(-1, indexOfFirstRepeatingElement);
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