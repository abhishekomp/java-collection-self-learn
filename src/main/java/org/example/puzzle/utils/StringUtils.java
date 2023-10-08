package org.example.puzzle.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class StringUtils {
    /**
     * Get the count of each character from a string in the form of a map with key as character and value as its count(in Long)
     *
     * @param input String input
     * @return a map with key as character and value as count of the character(as String) in the input string
     */
    public Map<String, Long> charToCount(String input) {
        Map<String, Long> stringLongMap = Arrays.stream(input.split(""))
                .collect(groupingBy(Function.identity(), counting()));
        return stringLongMap;
//        Map<Integer, Long> collect = input.chars()
//                .boxed()
//                .collect(groupingBy(Function.identity(), counting()));
    }

    /**
     * Get the count of each character from a string in the form of a map with key as character and value as its count(in int)
     *
     * @param input String input
     * @return a map with key as character and value as count of the character(as String) in the input string
     */
    public Map<String, Integer> charToCountAsInt(String input) {
        Map<String, Integer> stringIntegerMap = Arrays.stream(input.split(""))
                .collect(groupingBy(Function.identity(), collectingAndThen(counting(), Long::intValue)));
        return stringIntegerMap;
    }
}
