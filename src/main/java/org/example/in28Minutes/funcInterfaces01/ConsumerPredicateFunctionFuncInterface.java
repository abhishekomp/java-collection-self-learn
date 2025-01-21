package org.example.in28Minutes.funcInterfaces01;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConsumerPredicateFunctionFuncInterface {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        Predicate<Integer> isEvenNumber = new Predicate<Integer>() {
            @Override
            public boolean test(Integer num) {
                return num % 2 == 0;
            }
        };

        Function<Integer, Integer> squared = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer num) {
                return num * num;
            }
        };

        //Consumer<String> sysOutConsumer = str -> System.out.println(str);
        Consumer<String> sysOutConsumer = System.out::println;

//        numbers.stream()
//                .filter(x -> x % 2 == 0)
//                .map(x -> x * x)
//                .forEach(System.out::println);

        numbers.stream()
                .filter(isEvenNumber)
                .map(squared)
                .forEach(System.out::println);
    }
}
