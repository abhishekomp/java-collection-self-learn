package org.example.puzzle.sudoku_clash;

import java.util.List;

public class SudokuRunner {
    public static void main(String[] args) {
        SudokuClashSolver sudokuClashSolver = new SudokuClashSolver();
//        int[][] board = {
//                {5, 3, 4, 6, 7, 8, 9, 1, 2},
//                {6, 7, 2, 1, 9, 5, 3, 4, 8},
//                {1, 9, 8, 3, 4, 2, 5, 6, 7},
//                {8, 5, 9, 7, 6, 1, 4, 2, 3},
//                {4, 2, 6, 8, 5, 3, 7, 9, 1},
//                {7, 1, 3, 9, 2, 4, 8, 5, 6},
//                {9, 6, 1, 5, 3, 7, 2, 8, 4},
//                {2, 8, 7, 4, 1, 9, 6, 3, 5},
//                {3, 4, 5, 2, 8, 6, 1, 7, 9}
//        };
        int[][] board = {
                {5, 3, 4, 0, 7, 0, 9, 1, 2},
                {6, 7, 0, 1, 0, 5, 3, 4, 8},
                {1, 0, 8, 3, 4, 2, 5, 6, 7},
                {0, 5, 0, 7, 6, 0, 4, 2, 3},
                {4, 0, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 0, 9, 2, 4, 8, 5, 6},
                {0, 6, 1, 0, 3, 0, 2, 8, 4},
                {2, 8, 0, 4, 1, 0, 6, 3, 5},
                {3, 4, 5, 2, 8, 0, 1, 7, 9}
        };
        //List<Integer> board = List.of(5, 3, 4, 6, 7, 8, 9, 1, 2, 6, 7, 2, 1, 9, 5, 3, 4, 8, 1, 9, 8, 3, 4, 2, 5, 6, 7, 8, 5, 9, 7, 6, 1, 4, 2, 3, 4, 2, 6, 8, 5, 3, 7, 9, 1, 7, 1, 3, 9, 2, 4, 8, 5, 6, 9, 6, 1, 5, 3, 7, 2, 8, 4, 2, 8, 7, 4, 1, 9, 6, 3, 5, 3, 4, 5, 2, 8, 6, 1, 7, 9);
        //List<Integer> board = List.of(5, 3, 4, 6, 7, 8, 9, 1, 2, 6, 7, 2, 1, 9, 5, 3, 4, 8, 1, 9, 8, 3, 4, 2, 5, 6, 7, 8, 5, 9, 7, 6, 1, 4, 2, 3, 4, 2, 6, 8, 5, 3, 7, 9, 1, 7, 1, 3, 9, 2, 4, 8, 5, 6, 9, 6, 1, 5, 3, 7, 2, 8, 4, 2, 8, 7, 4, 1, 9, 6, 3, 5, 3, 4, 5, 2, 8, 6, 1, 7, 9);
        List<Boolean> booleans = sudokuClashSolver.level1ClashesInRows(board);
        System.out.println("booleans = " + booleans);
        if(booleans.contains(true)) {
            System.out.println("True found at " + booleans.indexOf(true));
        } else {
            System.out.println("True not found");
        }
    }
}
