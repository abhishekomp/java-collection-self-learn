package org.example.puzzle.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class ArraySolver {
    boolean isDuplicatePresent(int[] arr) {
        return true;
    }

    /**
     * Convert one dimensional array to a List
     * @param intArr primitive int array
     * @return  list of Integer objects
     */
    public List<Integer> convertIntArrayToList (int[] intArr) {
        return Arrays.stream(intArr).boxed().collect(toList());
    }

    /**
     * Convert a List of Integer objects to one dimensional array of primitive int
     * @param integerList List containing Integer objects
     * @return  primitive int array
     */
    public int[] convertListToIntArray(List<Integer> integerList) {
        int[] toArray = integerList.stream().mapToInt(i -> i).toArray();
        //int[] toArray = integerList.stream().mapToInt(Integer::intValue).toArray();
        return toArray;
    }

    public void display2DArray(int[][] board) {
        for(int[] row : board) {
            String rowItemsAsCommaSeparatedString = Arrays.stream(row)
                    .boxed()
                    .map(String::valueOf)
                    .collect(joining(", "));
            System.out.println(rowItemsAsCommaSeparatedString);
        }
    }

    int[] generateIntArray(int endValue) {
        int[] ints = IntStream.range(1, endValue).toArray();
        return ints;
    }

    /**
     * Gets the first duplicate element in a given integer array
     * @param inputArr an array of primitive ints
     * @return the first duplicate element
     */
    public int getTheFirstDuplicateElement(int[] inputArr) {
        Optional<Integer> firstDuplicate = Arrays.stream(inputArr)
                .boxed()
                .collect(groupingBy(Function.identity(), LinkedHashMap::new, counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst();
        int result = firstDuplicate.orElse(Integer.MIN_VALUE);
        System.out.println("First duplicate element is: " + result);
        return result;
    }

    public int getIndexOfFirstRepeatingElement(int[] intArr) {
        return -1;
    }

    public static void main(String[] args) {
        ArraySolver arraySolver = new ArraySolver();
//        List<Integer> integers = arraySolver.convertIntArrayToList(new int[]{1, 2, 3});
//        System.out.println("integers = " + integers);

        int[][] board = new int[][]{
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
        arraySolver.display2DArray(board);
    }
}
