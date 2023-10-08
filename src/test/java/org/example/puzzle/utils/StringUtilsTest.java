package org.example.puzzle.utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    StringUtils stringUtils = new StringUtils();

    @Test
    void test_should_return_correct_char_count() {
        String input = "abc";
        Map<String, Long> stringLongMap = stringUtils.charToCount(input);
        System.out.println("stringLongMap = " + stringLongMap);
        assertEquals(1L, stringLongMap.get("a"));
        assertEquals(1L, stringLongMap.get("b"));
        assertEquals(1L, stringLongMap.get("c"));
    }

    @Test
    void test_should_return_correct_char_count_for_abcc() {
        String input = "abcc";
        Map<String, Long> stringLongMap = stringUtils.charToCount(input);
        System.out.println("stringLongMap = " + stringLongMap);
        assertEquals(1L, stringLongMap.get("a"));
        assertEquals(1L, stringLongMap.get("b"));
        assertEquals(2L, stringLongMap.get("c"));

    }

    @Test
    void charToCountAsInt() {
        String input = "abcc";
        Map<String, Integer> stringLongMap = stringUtils.charToCountAsInt(input);
        System.out.println("stringLongMap = " + stringLongMap);
        assertEquals(1, stringLongMap.get("a"));
        assertEquals(1, stringLongMap.get("b"));
        assertEquals(2, stringLongMap.get("c"));

    }
}