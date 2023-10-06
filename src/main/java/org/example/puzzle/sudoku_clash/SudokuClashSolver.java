package org.example.puzzle.sudoku_clash;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class SudokuClashSolver {

    public static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
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
        SudokuClashSolver sudokuClashSolver = new SudokuClashSolver();
        int[] ints = sudokuClashSolver.convert2DArrayTo1DArray(board);
    }

    /**
     * Gets the row from the Sudoku board
     * @param board
     * @param row Ranges from 0 till 8
     * @return An integer array with elements from the requested row
     */
    int[] getRow(int[][] board, int row) {
        int[] rowArray = new int[GRID_SIZE];
        for(int i = 0; i < GRID_SIZE; i++) {
            rowArray[i] = board[row][i];
        }
        return rowArray;
    }
    /**
     * Gets the column data from the Sudoku board
     * @param board
     * @param col Ranges from 0 till 8
     * @return An integer array with elements from the requested column
     */
    int[] getColumn(int[][] board, int col) {
        int[] colArray = new int[GRID_SIZE];
        for(int i = 0; i < GRID_SIZE; i++) {
            colArray[i] = board[i][col];
        }
        return colArray;
    }

    /**
     * Gets the data from the inner square of the Soduko board. There are 9 inner squares indexed from 0 till 8
     * @param board 2D integer array representing the Sudoku board
     * @param squareIndex Ranges from 0 till 8
     * @return An integer array with elements from the requested inner square
     */
    int[] getInnerSquareData(int[][] board, int squareIndex) {
        //int[] squareArray = new int[GRID_SIZE];
        List<Integer> squareBoxElements = new ArrayList<>();
        int startWithRow = (squareIndex / 3) * 3;
        int startWithCol = (squareIndex % 3) * 3;
        for(int i = startWithRow; i <= (startWithRow + 2); i++) {
            for(int j = startWithCol; j <= (startWithCol + 2); j++) {
                squareBoxElements.add(board[i][j]);
            }
        }
        return squareBoxElements.stream().mapToInt(i -> i).toArray();
    }

    private int[] convert2DArrayTo1DArray(int[][] board) {
        int[] intArray1D = Arrays.stream(board).flatMapToInt(Arrays::stream).toArray();
        System.out.println(Arrays.toString(intArray1D));
        return intArray1D;
    }

    /**
     * Find clashes in all rows. Ignore other clashes, such as in columns or
     * in 3x3 boxes.
     * The input is an 81-long list of integers where zero denotes an empty
     * cell. Integers 1-9 represents a filled cell. See the description to
     * the left for a detailed description of how the indexing of the list
     * corresponds to the cells of the sudoku board.
     * A clash is whenever two or more cells in one of the nine rows contains
     * the same number, which is not allowed in a sudoku solution.
     * Return an 81-long list of booleans where True means the number in the
     * corresponding cell clashes with at least one other number in the same
     * row. All other cells should be False.
     */
    public List<Boolean> level1ClashesInRows(List<Integer> board) {
        // Write your code here
        // Split the board in 9 parts (each part is a 1-D int array)
        List<List<Integer>> lists = extractRowsOfSizeN(board);

        // Validate each row and if there is an element in a row that is duplicate, then break
        List<Boolean> finalBooleanList = new ArrayList<>();
        for(List<Integer> row: lists) {
            int indexOfFirstDuplicateElement = indexOfFirstDuplicateElement(row);
            List<Boolean> booleanList;
            booleanList = modifyListContentsByTrueFalse(row, indexOfFirstDuplicateElement);
//            if(indexOfFirstDuplicateElement >= 0) {
//                booleanList = modifyListContentsByTrueFalse(row, indexOfFirstDuplicateElement);
//            } else {
//                booleanList = modifyListContentsByTrueFalse(row, -1);
//            }
            finalBooleanList.addAll(booleanList);
        }

        // Return an 81-long list of boolean as mentioned above
        return finalBooleanList;
    }

    /**
     * Find clashes in all rows. Ignore other clashes, such as in columns or
     * in 3x3 boxes.
     * The input is an 81-long list of integers where zero denotes an empty
     * cell. Integers 1-9 represents a filled cell. See the description to
     * the left for a detailed description of how the indexing of the list
     * corresponds to the cells of the sudoku board.
     * A clash is whenever two or more cells in one of the nine rows contains
     * the same number, which is not allowed in a sudoku solution.
     * Return an 81-long list of booleans where True means the number in the
     * corresponding cell clashes with at least one other number in the same
     * row. All other cells should be False.
     */
    public List<Boolean> level1ClashesInRows(int[][] board) {
        int[] ints = convert2DArrayTo1DArray(board);
        List<Integer> integerList = Arrays.stream(ints).boxed().collect(toList());
        return level1ClashesInRows(integerList);
    }

    List<List<Integer>> extractRowsOfSizeN(List<Integer> board) {
        List<List<Integer>> listOfIntegerList = new ArrayList<>();
        //List<Integer> rowList = new ArrayList<>();
        int fromIndex, toIndex;
        for(int i = 0; i < GRID_SIZE; i++) {
            List<Integer> rowList = new ArrayList<>();
            fromIndex = i * GRID_SIZE;
            toIndex = ((i + 1) * GRID_SIZE) - 1;
            for(int j = fromIndex; j <= toIndex; j++) {
                rowList.add(board.get(j));
            }
            listOfIntegerList.add(rowList);
        }
        return listOfIntegerList;
    }

    /**
     * Returns the index of first duplicate element in the list (in this case 0 is not considered to be an element)
     * @param list the list with Integers
     * @return  index of the first duplicate element
     */
    int indexOfFirstDuplicateElement(List<Integer> list){
        List<Integer> collect = list.stream()
                .collect(groupingBy(Function.identity(), LinkedHashMap::new, counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1 && e.getKey() != 0)
                .map(Map.Entry::getKey)
                .collect(toList());
        if(collect.size() > 0) {
            return list.indexOf(collect.get(0));
        }
        return -1;
    }

    List<Boolean> modifyListContentsByTrueFalse(List<Integer> list, int indexForTrue) {
        List<Boolean> booleanList = new ArrayList<>();
        if(indexForTrue == -1) {
            for(int i = 0; i < list.size(); i++) {
                booleanList.add(false);
            }
            return booleanList;
        }
        for(int i = 0; i < list.size(); i++) {
            if(i == indexForTrue) {
                booleanList.add(true);
            }
            booleanList.add(false);
        }
        return booleanList;
    }
}
/*
True board
int[][] board = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

validSolution([
  [5, 3, 4, 6, 7, 8, 9, 1, 2],
  [6, 7, 2, 1, 9, 0, 3, 4, 8],
  [1, 0, 0, 3, 4, 2, 5, 6, 0],
  [8, 5, 9, 7, 6, 1, 0, 2, 0],
  [4, 2, 6, 8, 5, 3, 7, 9, 1],
  [7, 1, 3, 9, 2, 4, 8, 5, 6],
  [9, 0, 1, 5, 3, 7, 2, 1, 4],
  [2, 8, 7, 4, 1, 9, 6, 3, 5],
  [3, 0, 0, 4, 8, 1, 1, 7, 9]
]); // => false
 */