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

    //Given a List<String> of words, create a list of lengths of words that start with the letter "a".
    @Test
    void test3() {
        Set<String> words = Set.of("apple", "banana", "avocado", "grape", "apricot", "kiwi");
        Set<Integer> lengths = words.stream()
                .filter(word -> word.startsWith("a"))
                .map(String::length)
                .collect(Collectors.toSet());
        System.out.println("Lengths of words starting with 'a': " + lengths);
        Set<Integer> expectedLengths = Set.of(5, 7, 8); // "apple", "avocado", "apricot"
        assertEquals(expectedLengths, lengths);
    }

    //Given a list of Person objects (with name and age), create a map of name to age for all persons older than 18.
    @Test
    void test4() {
        Set<Person> persons = Set.of(
                new Person("Alice", 25),
                new Person("Bob", 17),
                new Person("Charlie", 20),
                new Person("David", 15)
        );

        Map<String, Integer> nameToAgeMap = persons.stream()
                .filter(person -> person.getAge() > 18)
                .collect(Collectors.toMap(Person::getName, Person::getAge));

        System.out.println("Name to age map for persons older than 18: " + nameToAgeMap);
        Map<String, Integer> expectedMap = Map.of("Alice", 25, "Charlie", 20);
        assertEquals(expectedMap, nameToAgeMap);
    }

    //Given a list of integers, create a set of their squares.
    @Test
    void test5() {
        Set<Integer> integers = Set.of(1, 2, 3, 4, 5);
        Set<Integer> squares = integers.stream()
                .map(i -> i * i)
                .collect(Collectors.toSet());
        System.out.println("Set of squares: " + squares);
        Set<Integer> expectedSquares = Set.of(1, 4, 9, 16, 25);
        assertEquals(expectedSquares, squares);

        //Using reduce to solve the same problem
        Set<Integer> squaresUsingReduce = integers.stream()
                .map(i -> i * i)
                .reduce(new HashSet<>(), (set, square) -> {
                    set.add(square);
                    return set;
                }, (set1, set2) -> {
                    set1.addAll(set2);
                    return set1;
                });
    }

    @Test
    void test6() {
        // Given a list of strings, use reduce to concatenate them all into a single
        Set<String> words = Set.of("apple", "banana", "avocado", "grape", "apricot", "kiwi");
        String concatenated = words.stream()
                .reduce("", (partialString, element) -> partialString + element + " ");

        // Trim the result to remove the trailing space
        concatenated = concatenated.trim();
        System.out.println("Concatenated string: " + concatenated);
        String expectedConcatenated = "apple banana avocado grape apricot kiwi";
        assertEquals(expectedConcatenated, concatenated);

        // Solve the same problem using collect
        String concatenatedUsingCollect = words.stream()
                .collect(Collectors.joining(" "));
        System.out.println("Concatenated string using collect: " + concatenatedUsingCollect);
        assertEquals(expectedConcatenated, concatenatedUsingCollect);
    }

}
