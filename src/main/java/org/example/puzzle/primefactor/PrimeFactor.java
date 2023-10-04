package org.example.puzzle.primefactor;

import java.util.ArrayList;
import java.util.List;

/*
Your task is to write a program that takes an integer as input and returns a list of its prime factors.

For example, the prime factors of 15 are 3 and 5.

Input: An integer n, where 2 <= n <= 10.000
Output: A list of integers representing the prime factors of n in ascending order.
Example:

Input: 20
Output: [2, 2, 5]
*/

public class PrimeFactor {
    public static void main(String[] args) {
        //long number = Long.parseLong(args[0]);
        long number = 24;
        List<Long> primeFactors = getPrimeFactors(number);
        //primeFactors.forEach(System.out::println);
        System.out.println(primeFactors);
    }

    public static List<Long> getPrimeFactors(long number) {
        List<Long> factorList = new ArrayList<>();
        for (long i=2; i <= number/i; i++) {
            System.out.println("{ i = " + i + " , number = " + number + "}");
            while(number%i == 0) {
                number = number/i;
                //System.out.println("Factor: " + i);
                factorList.add(i);
            }
        }
        if(number > 1) {
            //System.out.println("Factor: " + number);
            factorList.add(number);
        }
        return factorList;
        //System.out.println();
    }
}
