// This is commented because it is the copied content from https://futureskill.com/
//package org.example.puzzle.tictactoe;
//
//public class ETraveliTicTacToe {
//}
//
///*
//package solution;
//
//import api.*;
//import java.util.*;
//import static java.util.stream.Collectors.counting;
//import static java.util.stream.Collectors.groupingBy;
//import java.util.stream.Collectors;
//import java.util.function.Function;
//
//public class Solution implements SolutionInterface {
//    private APICaller api;
//
//    public Solution(APICaller api) {
//        this.api = api;
//        System.out.println("Press run code to see this in the console!");
//        // You can initiate and calculate things here
//    }
//
//    /**
//     * Return the string for the middle square; "X", "O" or "". The input
//     * 'board' parameter represent all nine squares, the current board state,
//     * in a list with the first index, zero, in the bottom left (x=0, y=0).
//     * Index 1 is (x=1, y=0), index 2 is (x=2, y=0), index 3 is (x=0, y=1)
//     * and so forth. See the Description for a visual reference.
//     */
//public String level1MiddleSquare(List<String> board) {
//    // Write your code here
//    String middleSquare = board.get(4);
//    return middleSquare;
//}
//
//    /**
//     * Level 2 - Return the number of circles / "O" which are on the board.
//     * The input 'board' parameter represent all nine squares, the current
//     * board state, in a list with the first index, zero, in the bottom left
//     * (x=0, y=0). Index 1 is (x=1, y=0), index 2 is (x=2, y=0), index 3 is
//     * (x=0, y=1) and so forth. See the Description for a visual reference.
//     */
//    public int level2NumberOfO(List<String> board) {
//        // Write your code here
//        //System.out.println("Test");
//        //Map<String, Integer> countBySymbol = board.stream()
//        //    .collect(Collectors.groupingBy(Function.identity(), Collectors.collectingAndThen(counting(), Long::intValue)));
//        long count = board.stream().filter(element -> "O".equals(element)).count();
//        return (int) count;
//    }
//
//    /**
//     * Level 3 - Does circles / "O" have three in a row? The input 'board'
//     * parameter represent all nine squares, the current board state, in a
//     * list with the first index, zero, in the bottom left (x=0, y=0). Index
//     * 1 is (x=1, y=0), index 2 is (x=2, y=0), index 3 is (x=0, y=1) and so
//     * forth. See the Description for a visual reference.
//     */
//    public boolean level3DoesOHave3InARow(List<String> board) {
//        List<Integer> row1 = List.of(0, 1, 2);
//        List<Integer> row2 = List.of(3, 4, 5);
//        List<Integer> row3 = List.of(6, 7, 8);
//        List<Integer> row4 = List.of(0, 3, 6);
//        List<Integer> row5 = List.of(1, 4, 7);
//        List<Integer> row6 = List.of(2, 5, 8);
//        List<Integer> row7 = List.of(0, 4, 8);
//        List<Integer> row8 = List.of(2, 4, 6);
//
//        List<List<Integer>> winList = List.of(row1, row2, row3, row4, row5, row6, row7, row8);
//        boolean result = false;
//        for(List<Integer> winCoordinates : winList) {
//            String pos0 = board.get(winCoordinates.get(0));
//            System.out.println("pos0: " + pos0);
//            String pos1 = board.get(winCoordinates.get(1));
//            String pos2 = board.get(winCoordinates.get(2));
//            if((pos0.equals("O")) && (pos0.equals(pos1)) && (pos1.equals(pos2))) {
//                //if((board.get(winCoordinates.get(0)) == board.get(winCoordinates.get(1)) == board.get(winCoordinates.get(2)) == "O")) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }
//}
// */
