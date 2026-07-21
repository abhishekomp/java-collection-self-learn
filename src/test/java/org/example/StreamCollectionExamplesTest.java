package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamCollectionExamplesTest {

    /**
     * Count how many *distinct* characters appear more than once in a given string.
     * <p>
     * Example: "rollerl" → 'r' repeats, 'l' repeats → answer is 2.
     * <p>
     * Fix: the previous implementation computed (length - uniqueCount) which gives
     * the total number of *extra* occurrences across all characters (3 for "rollerl"),
     * not the number of distinct repeating characters (2). We now group by character
     * and count how many groups have a frequency > 1.
     */
    @Test
    void test_find_the_number_of_characters_that_repeat_in_the_given_string() {
        String inputStr = "rollerl";
        int expectedResult = 2; // 'r' and 'l' both appear more than once

        // JDK 21: Stream.toList() (available since Java 16) replaces collect(toList())
        long actualResult = inputStr.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .values().stream()
                .filter(count -> count > 1)
                .count();

        System.out.println("Number of distinct repeating characters: " + actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test2() {
        // Count frequency of each character in "sheep"
        Map<String, Long> collect = "sheep"
                .chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
        collect.forEach((k, v) -> System.out.println(k + "->" + v));
    }

    /**
     * Given a List of words, collect the *distinct* lengths of words that start with "a".
     * <p>
     * Fix: the previous expected set included 8 (allegedly for "apricot"), but
     * "apricot" is 7 characters long. The correct distinct lengths for words starting
     * with 'a' in {"apple"(5), "avocado"(7), "apricot"(7)} are {5, 7}.
     */
    @Test
    void test3() {
        Set<String> words = Set.of("apple", "banana", "avocado", "grape", "apricot", "kiwi");
        Set<Integer> lengths = words.stream()
                .filter(word -> word.startsWith("a"))
                .map(String::length)
                .collect(Collectors.toSet());
        System.out.println("Lengths of words starting with 'a': " + lengths);

        // "apple"=5, "avocado"=7, "apricot"=7  →  distinct lengths: {5, 7}
        // (previously expected Set.of(5, 7, 8) which was wrong)
        Set<Integer> expectedLengths = Set.of(5, 7);
        assertEquals(expectedLengths, lengths);
    }

    // Given a list of Person objects (with name and age), create a map of name to age for all persons older than 18.
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

    // Given a list of integers, create a set of their squares.
    @Test
    void test5() {
        Set<Integer> integers = Set.of(1, 2, 3, 4, 5);
        Set<Integer> squares = integers.stream()
                .map(i -> i * i)
                .collect(Collectors.toSet());
        System.out.println("Set of squares: " + squares);
        Set<Integer> expectedSquares = Set.of(1, 4, 9, 16, 25);
        assertEquals(expectedSquares, squares);

        // Using reduce to solve the same problem (note: mutable-reduction via reduce is
        // technically incorrect for parallel streams; prefer collect() in production code)
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

    /**
     * Concatenate all words in a set using reduce and then using Collectors.joining.
     * <p>
     * Fix: a {@code Set} has no defined iteration order, so concatenating with reduce
     * produces a non-deterministic result.  We now sort the words before joining so
     * the test is deterministic.  The previous assertion compared against a hard-coded
     * order which matched one particular JVM run but failed on others.
     */
    @Test
    void test6() {
        Set<String> words = Set.of("apple", "banana", "avocado", "grape", "apricot", "kiwi");

        // Sort first to make the result deterministic, then join
        String concatenated = words.stream()
                .sorted()
                .collect(Collectors.joining(" "));
        System.out.println("Concatenated string: " + concatenated);

        // Expected: alphabetically sorted
        String expectedConcatenated = "apple apricot avocado banana grape kiwi";
        assertEquals(expectedConcatenated, concatenated);

        // Solve the same problem using reduce (same sort required for determinism)
        String concatenatedUsingReduce = words.stream()
                .sorted()
                .reduce("", (partialString, element) ->
                        partialString.isEmpty() ? element : partialString + " " + element);
        System.out.println("Concatenated string using reduce: " + concatenatedUsingReduce);
        assertEquals(expectedConcatenated, concatenatedUsingReduce);
    }

}
