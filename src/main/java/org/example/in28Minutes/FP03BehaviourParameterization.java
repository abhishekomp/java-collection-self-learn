package org.example.in28Minutes;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @author : abhishekomprakash
 * @since : Tue, 2025-Jan-14
 * Created with IntelliJ IDEA
 */

public class FP03BehaviourParameterization {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        Function<Integer, Integer> squareFunction = x -> x * x;
        Function<Integer, Integer> cubeFunction = x -> x * x * x;

        List<Integer> squaredNumbers = mapAndCollect(numbers, squareFunction);
        squaredNumbers.forEach(System.out::println);
    }

    // The takeaway from this demo is that we are passing function behaviour to this method. We can pass another behaviour for example to perform cube.
    // Take a look at the squareFunction and the cubeFunction that can be passed to the mapAndCollect method. So a user of this method can send an instance of Function Interface.
    private static List<Integer> mapAndCollect(List<Integer> numbers, Function<Integer, Integer> mapperFunction) {
        return numbers.stream()
                .map(mapperFunction)
                .collect(toList());
    }
}
