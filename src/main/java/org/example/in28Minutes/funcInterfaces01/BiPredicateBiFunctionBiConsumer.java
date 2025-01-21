package org.example.in28Minutes.funcInterfaces01;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class BiPredicateBiFunctionBiConsumer {

    public static void main(String[] args) {

        //BiPredicate Example
        BiPredicate<Integer, String> biPredicate = (integer, str) -> true;

        BiPredicate<Integer, String> biPredicate2 = (integer, str) -> {
            return integer < 10 && str.length() > 5;
        };

        System.out.println(biPredicate.test(10, "javaFunctionalInterfaces"));
        System.out.println(biPredicate2.test(8, "javaFunctionalInterfaces"));
        System.out.println(biPredicate2.test(8, "java"));

        //BiFunction Example
        BiFunction<Integer, String, String> biFunction = (number, str) -> {
            return number + " " + str;
        };
        System.out.println(biFunction.apply(20, "years ahead"));

        //BiConsumer Example
        BiConsumer<Integer, String> biConsumer = (number, str) -> {
            System.out.println(number + " " + str);
        };
        biConsumer.accept(25, "years ahead");

        // Supplier Functional interface example.
        Supplier<String> supplier = () -> {
            return "functional interface";
        };
    }
}
