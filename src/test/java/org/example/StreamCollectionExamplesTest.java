package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamCollectionExamplesTest {

    // Count how many characters repeat in a given string
    @Test
    void test_find_the_number_of_characters_that_repeat_in_the_given_string(){
        Set<Character> set = new HashSet<>();
        //String inputStr = "app";
        //int expectedResult = 1;
        String inputStr = "roller";
        int expectedResult = 2;

        int sum = inputStr.chars()
                .mapToObj(c -> (char) c)
                .mapToInt(c -> (set.add(c) ? 1 : 0))
                .sum();

        int actualResult = inputStr.length() - sum;
        System.out.println("Number of repeating characters: " + actualResult);
        assertEquals(expectedResult, actualResult);
    }
}
