package org.example;

import org.example.puzzle.utils.ArraySolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsLambdaStreamTest {

    private List<Dog> dogs;

    @BeforeEach
    void setUp() {
        dogs = Dog.getDogs();
    }

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
    @Test
    void test_should_sum_age_of_all_dogs(){
        Integer sumOfAges = dogs.stream()
                .map(Dog::getAge)
                .reduce((total, age) -> total + age).orElse(0);
        System.out.println("sumOfAges = " + sumOfAges);
        assertEquals(sumOfAges, 51);
    }

    @Test
    void test_sum_IntStream() {
        //IntStream.of(1,2,3,4,5).sum();
        assertEquals(IntStream.of(1,2,3,4,5).sum(), 15);
    }

    @Test
    void test_reduce_using_identity_accumulator_as_lambda() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        assertEquals(result, 21);
    }

    @Test
    void test_reduce_using_identity_accumulator_as_method_reference() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers
                .stream()
                .reduce(0, Integer::sum);
        assertEquals(result, 21);
    }
    @Test
    void test_reduce_to_calculate_sum_of_ages_of_person_objects(){
        List<Person> people = List.of(
                new Person("Jack", 15),
                new Person("Sara", 20),
                new Person("Bob", 20),
                new Person("Paula", 35)
        );
        Integer sumOfAges = people.stream()
                .reduce(0, (partialResult, user) -> partialResult + user.getAge(), Integer::sum);
        assertEquals(sumOfAges, 90);
    }

    @Test
    void test_reduce_to_calculate_sum_of_ages_of_person_objects_using_sum(){
        List<Person> people = List.of(
                new Person("Jack", 15),
                new Person("Sara", 20),
                new Person("Bob", 20),
                new Person("Paula", 35)
        );
        Integer sumOfAges = people.stream()
                .mapToInt(Person::getAge)
                .sum();
        assertEquals(sumOfAges, 90);
    }

    // Given a list of dog objects (name, breed, age) group them by breed and get the max age for each breed.
    @Test
    void test_get_max_age_per_group_of_breed_of_dogs() {
        dogs.forEach(System.out::println);
        Map<String, Integer> maxAgeByBreed = dogs.stream()
                .collect(groupingBy(Dog::getBreed, collectingAndThen(maxBy(comparing(Dog::getAge)), dog -> dog.map(Dog::getAge).orElse(0))));
        System.out.println("maxAgeByBreed = " + maxAgeByBreed);
    }
}
