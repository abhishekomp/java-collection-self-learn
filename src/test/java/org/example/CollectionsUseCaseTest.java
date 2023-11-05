package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsUseCaseTest {

    //computeIfAbsent
    @Test
    void test_computeIfAbsent_should_return_the_existing_mapped_value_if_key_is_associated_with_a_value() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 5);
        Integer value = map.computeIfAbsent("John", String::length);
        assertEquals(value, 5);
        // It’s important to know that the mapping function is only called if the mapping isn’t present.
        //computeIfAbsent() : First, it checks if the key is present in the map. If the key is present, and a non-null value is related to the key, then it returns that value.
        //As we can see, the key “John” has a non-null mapping present, and it returns the value 5. If our mapping function was used, we’d expect the function to return the length of 4.
    }

    //computeIfAbsent
    @Test
    void test_value_is_computed_when_key_is_not_present_in_the_map(){
        Map<String, Integer> map = new HashMap<>();
        Integer value = map.computeIfAbsent("John", String::length);
        assertEquals(value, 4);
        assertEquals(map.get("John"), 4);
    }

    //computeIfAbsent
    @Test
    void test_value_is_computed_when_key_is_associated_with_null_in_the_map(){
        Map<String, Integer> map = new HashMap<>();
        map.put("John", null);
        Integer value = map.computeIfAbsent("John", String::length);
        assertEquals(value, 4);
        assertEquals(map.get("John"), 4);
    }

    @Test
    void test_computeIfAbsent_will_return_current_value_if_key_has_non_null_value_associated_with_it(){
        Map<String, Integer> stringLength = new HashMap<>();
        stringLength.put("John", 5);
        assertEquals(stringLength.computeIfAbsent("John", String::length), 5);
    }

    @Test
    void test_computeIfAbsent_will_return_computed_value_if_key_has_null_value_associated_with_it(){
        Map<String, Integer> stringLength = new HashMap<>();
        stringLength.put("John", null);
        assertEquals(stringLength.computeIfAbsent("John", String::length), 4);
        System.out.println("stringLength = " + stringLength);
        assertEquals(stringLength.get("John"), 4);
    }

    @Test
    void test_computeIfAbsent_will_return_computed_value_if_key_is_not_present_in_the_map(){
        Map<String, Integer> stringLength = new HashMap<>();
        //stringLength.put("John", null);
        assertEquals(stringLength.computeIfAbsent("John", String::length), 4);
        System.out.println("stringLength = " + stringLength);
        assertEquals(stringLength.get("John"), 4);
    }
}
