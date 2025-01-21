package org.example.in28Minutes.funcInterfaces01;

import java.util.List;
import java.util.function.*;

public class BinaryOperatorFuncInterface {
    public static void main(String[] args) {

        // In this demo, we will filter out even numbers, square them and then add all the squared values.
        // BinaryOperator is a Functional Interface that extends another Functional Interface named BiFunction
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

        Consumer<Integer> sysOutConsumerImpl = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };

//        numbers.stream()
//                .filter(x -> x % 2 == 0)
//                .map(x -> x * x)
//                .forEach(System.out::println);

        BinaryOperator<Integer> binaryOperatorSum = Integer::sum;
        BinaryOperator<Integer> binaryOperatorSum2 = (num1, num2) -> num1 + num2;

        BinaryOperator<Integer> sumBinaryOperator = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer num1, Integer num2) {
                return num1 + num2;
            }
        };

        Integer sum = numbers.stream()
                .filter(isEvenNumber)
                .map(squared)
                .reduce(0, binaryOperatorSum2);

        System.out.println(sum);


        // UnaryOperator is a special type of Function that takes an input of type T and returns a result of the same type.
        UnaryOperator<Integer> unaryOperator = (x) -> 10 * x;
        System.out.println("UnaryOperator's apply method returns result of the same type as the argument, in this case: " + unaryOperator.apply(100));   // should print 1000
    }
}
