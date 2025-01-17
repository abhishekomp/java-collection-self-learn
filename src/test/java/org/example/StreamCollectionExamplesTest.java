package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamCollectionExamplesTest {

    // Count how many characters repeat in a given string
    @Test
    void test_find_the_number_of_characters_that_repeat_in_the_given_string(){
        Set<Character> set = new HashSet<>();
        //String inputStr = "app";
        //int expectedResult = 1;
        String inputStr = "rollerl";
        int expectedResult = 2;

        int sum = inputStr.chars()
                .mapToObj(c -> (char) c)
                .mapToInt(c -> (set.add(c) ? 1 : 0))
                .sum();

        int actualResult = inputStr.length() - sum;
        System.out.println("Number of repeating characters: " + actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test2 () {
        // "The quick brown fox jumped over the lazy dog."
        Map<String, Long> collect = "sheep"
                .chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
        collect.forEach((k, v) -> System.out.println(k + "->" + v));
    }
}
