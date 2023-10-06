package org.example.puzzle.sudoku_clash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class SudokuClashSolverTest {

    SudokuClashSolver sudokuClashSolver;
    int[][] board = new int[9][9];

    @BeforeEach
    void setUp() {
        sudokuClashSolver = new SudokuClashSolver();

        board = new int[][]{
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 4, 9}
        };
    }

    @Test
    void getRow() {
        int rowNum = 5;
        int[] expectedArray = new int[] {7, 1, 3, 9, 2, 4, 8, 5, 6};

        int[] dataArray = sudokuClashSolver.getRow(board, rowNum);
        //convert array to list (convert 1D array to list)
        List<Integer> integerList = Arrays.stream(dataArray).boxed().collect(toList());
        String integersJoinedAsString = integerList.stream()
                .map(String::valueOf)
                .collect(joining(", "));
        System.out.println("Data from row = " + integersJoinedAsString);
        assertArrayEquals(expectedArray, dataArray);
    }

    @Test
    void getColumnData() {
        int colNum = 6;
        int[] expectedArray = new int[] {9, 3, 5, 4, 7, 8, 2, 6, 1};

        int[] dataArray = sudokuClashSolver.getColumn(board, colNum);
        List<Integer> integerList = Arrays.stream(dataArray).boxed().collect(toList());
        String integersJoinedAsString = integerList.stream()
                .map(String::valueOf)
                .collect(joining(", "));
        System.out.println("Data from row = " + integersJoinedAsString);
        assertArrayEquals(expectedArray, dataArray);
    }

    @Test
    void getInnerSquareData() {
        int innerSquareIndex = 3;
        int[] expectedArray = new int[] {8, 5, 9, 4, 2, 6, 7, 1, 3};

        int[] dataArray = sudokuClashSolver.getInnerSquareData(board, innerSquareIndex);
        List<Integer> integerList = Arrays.stream(dataArray).boxed().collect(toList());
        String integersJoinedAsString = integerList.stream()
                .map(String::valueOf)
                .collect(joining(", "));
        System.out.println("Data from inner square = " + integersJoinedAsString);
        assertArrayEquals(expectedArray, dataArray);
    }

    @Test
    void extractNthRow() {
        List<Integer> integers = List.of(5, 3, 4, 6, 7, 8, 9, 1, 2, 6, 7, 2, 1, 9, 5, 3, 4, 8, 1, 9, 8, 3, 4, 2, 5, 6, 7, 8, 5, 9, 7, 6, 1, 4, 2, 3, 4, 2, 6, 8, 5, 3, 7, 9, 1, 7, 1, 3, 9, 2, 4, 8, 5, 6, 9, 6, 1, 5, 3, 7, 2, 8, 4, 2, 8, 7, 4, 1, 9, 6, 3, 5, 3, 4, 5, 2, 8, 6, 1, 4, 9);
        List<List<Integer>> lists = sudokuClashSolver.extractRowsOfSizeN(integers);
        lists.forEach(System.out::println);

    }

    @Test
    void test_should_return_index_of_first_duplicate_element_in_the_list() {
        int expectedIndex = 3;
        List<Integer> integers = List.of(5, 7, 4, 6, 3, 8, 6, 1, 6);
        //List<Integer> integers = List.of(6, 7, 2, 1, 9, 5, 3, 4, 8);
        //5, 7, 4, 6, 3, 8, 9, 1, 2
        //6, 7, 2, 1, 9, 5, 3, 4, 8
        int actualIndex = sudokuClashSolver.indexOfFirstDuplicateElement(integers);
        System.out.println("actualIndex = " + actualIndex);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void test_should_return_negative_one_when_no_duplicate_element_in_the_list() {
        int expectedIndex = -1;
        List<Integer> integers = List.of(5, 7, 4, 6, 3, 8, 9, 1, 2);
        //5, 7, 4, 6, 3, 8, 9, 1, 2
        //6, 7, 2, 1, 9, 5, 3, 4, 8
        int actualIndex = sudokuClashSolver.indexOfFirstDuplicateElement(integers);
        System.out.println("actualIndex = " + actualIndex);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void test_should_return_correct_index_when_zero_is_in_the_list() {
        int expectedIndex = 4;
        List<Integer> integers = List.of(5, 0, 4, 0, 3, 8, 9, 3, 2);
        //5, 7, 4, 6, 3, 8, 9, 1, 2
        //6, 7, 2, 1, 9, 5, 3, 4, 8
        int actualIndex = sudokuClashSolver.indexOfFirstDuplicateElement(integers);
        System.out.println("actualIndex = " + actualIndex);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void test_should_return_correct_index_when_zero_is_in_the_list_another_test() {
        int expectedIndex = 1;
        List<Integer> integers = List.of(0, 4, 4, 0, 3, 8, 9, 3, 2);
        //5, 7, 4, 6, 3, 8, 9, 1, 2
        //6, 7, 2, 1, 9, 5, 3, 4, 8
        int actualIndex = sudokuClashSolver.indexOfFirstDuplicateElement(integers);
        System.out.println("actualIndex = " + actualIndex);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void test_should_modify_the_given_list_and_insert_True_at_correct_index() {
        int indexForTrue = 4;
        List<Integer> integers = List.of(0, 4, 4, 0, 3, 8, 9, 3, 2);
        List<Boolean> booleanList = sudokuClashSolver.modifyListContentsByTrueFalse(integers, indexForTrue);
        System.out.println("booleanList = " + booleanList);
        //booleanList.forEach(System.out::println);

    }
}