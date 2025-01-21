package org.example.in28Minutes.stream_customclass;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FP04ComparatorSorting {
    public static void main(String[] args) {

        List<String> courses = List.of("Spring", "Spring Boot", "AWS", "Lava", "Java", "Microservices", "API", "PCF", "Azure", "Docker", "Kubernetes");
        System.out.println(courses.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(toList()));

        // Sort the courses using the String length and when the String length is same for multiple courses, sort those courses in reverse alphabetical order.
        System.out.println(courses.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.reverseOrder()))
                .collect(toList()));


    }
}
