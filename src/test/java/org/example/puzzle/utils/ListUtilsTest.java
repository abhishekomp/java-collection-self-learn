package org.example.puzzle.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class ListUtilsTest {

    @Test
    void test_reverse_a_list_using_java8Stream() {
        List<String> stringList = List.of("World", "Hello");
        List<String> reverseList = ListUtils.reverse(stringList);
        System.out.println("stringList = " + stringList);
        System.out.println("reverseList = " + reverseList);
    }

    @Test
    void test_reverse_a_string() {
        String input = "Dog bites man";
        String expectedResult = "man bites Dog";

        List<String> inputStringList = Arrays.stream(input.split("\\s+")).collect(toList());

        //see the test case "test_reverse_a_list_using_java8Stream" where it is demonstrated how to reverse a list of string using Java 8 stream
        String reversedString = ListUtils.reverse(inputStringList).stream().collect(joining(" "));
        //String reversedString = String.join(" ", ListUtils.reverse(inputStringList));

        System.out.println("input = " + input);
        System.out.println("reversedString = " + reversedString);
    }
}