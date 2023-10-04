package org.example.puzzle.buysellstock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * 0th index should be called as Day 1 for the transaction
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 */
public class BuySellStockPuzzle {

    /** Gets the Buying Day
     * @param priceList An int array
     * @return The day when the price is minimum
     */
    public int getBuyDay(int[] priceList) {
        return buyDayIndex(priceList) + 1;
    }

    private int buyDayIndex(int[] priceList) {
        //Convert the integer array into a List
        List<Integer> priceL = Arrays.stream(priceList).boxed().collect(toList());

        //Get the minimum element and its index from the list
        int buyDayIndex = priceL.indexOf(Collections.min(priceL));
        int minPrice = Collections.min(priceL);

        return buyDayIndex;
    }

    /** Gets the Selling Day
     * @param priceList An int array
     * @param buyDay The buy day when the price is minimum
     * @return The day when the price is maximum after the buy day
     */
    public int getSellDay(int[] priceList, int buyDay) {
        //if buyDay is the last index in priceList then we don't have a sell day, return 0 in such case
        int sellDay = 0;
        if(buyDay == priceList.length - 1) {
            return sellDay;
        }
        // Find the index of the element which is max greater than the element at index buyDay
        // The element must be after the element at index buyDay
        List<Integer> priceL = Arrays.stream(priceList).boxed().collect(toList());
        List<Integer> priceSubL = priceL.subList(buyDay + 1, priceL.size());
        System.out.println("priceSubL = " + priceSubL);
        int maxSellPrice = Collections.max(priceSubL);
        if(maxSellPrice >= priceList[buyDay]) {
            sellDay = priceSubL.indexOf(maxSellPrice);
        }
        return sellDay + buyDay + 2;
    }

    public static void main(String[] args) {
        BuySellStockPuzzle buySellStockPuzzle = new BuySellStockPuzzle();
        //int[] priceList = new int[]{7, 1, 5, 3, 6, 4};
        int[] priceList = new int[]{7, 9, 5, 3, 6, 4, 1, 2, 3};
        //int[] priceList = new int[]{7, 6, 4, 3, 1};
        int buyDay = buySellStockPuzzle.getBuyDay(priceList);
        System.out.println("buyDay = " + buyDay);
        int sellDayIndex = buySellStockPuzzle.getSellDay(priceList, buyDay - 1);
        //System.out.println("sellDayIndex = " + sellDayIndex);
        System.out.println("sellDay = " + (sellDayIndex));
    }

}
