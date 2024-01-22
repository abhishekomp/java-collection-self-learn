package org.example.puzzle.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class StringUtils {
    /**
     * Get the count of each character from a string in the form of a map with key as character and value as its count(in Long)
     * Topic: Given a String, create a Map with key as the Character and value as the count of the Character. (String to Map)
     *
     * @param input String input
     * @return a map with key as character and value as count of the character(as String) in the input string
     */
    public Map<String, Long> stringToCount(String input) {
        Map<String, Long> charCountMap = Arrays.stream(input.split(""))
                .collect(groupingBy(Function.identity(), counting()));

//        Map<Integer, Long> charCountMap = input.chars()
//                .boxed()
//                .collect(groupingBy(Function.identity(), counting()));

        return charCountMap;
    }

    /**
     * Topic: Given a String, create a Map with key as the Character and value as the count of the Character. (String to Map)
     *
     * @param input String input
     * @return a map with key as character and value as count of the character(as String) in the input string
     */
    public Map<Character, Long> charToCount(String input) {
//        Map<String, Long> charCountMap = Arrays.stream(input.split(""))
//                .collect(groupingBy(Function.identity(), counting()));

//        Map<Integer, Long> charCountMap = input.chars()
//                .boxed()
//                .collect(groupingBy(Function.identity(), counting()));

        LinkedHashMap<Character, Long> charCountMap = input.chars().mapToObj(i -> (char) i)
                .collect(groupingBy(Function.identity(), LinkedHashMap::new, counting()));

        return charCountMap;
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

    /**
     * Extracts the first repeating (duplicate) word in a given sentence. Returns blank string if no duplicate exists.
     * @param input String that may or may not contain any duplicate (repeating) words
     * @return the first duplicate(repeating) word or else blank string
     */
    public String getFirstRepeatingWord(String input) {
        Optional<String> first = Arrays.stream(input.split("\\s+"))
                .collect(groupingBy(Function.identity(), LinkedHashMap::new, collectingAndThen(counting(), Long::intValue)))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst();
        String firstRepeatingWord = first.orElse("");
        //System.out.println("first = " + firstRepeatingWord);
        return firstRepeatingWord;
    }
}
