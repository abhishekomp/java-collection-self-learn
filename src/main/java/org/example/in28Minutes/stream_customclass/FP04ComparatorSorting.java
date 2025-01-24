package org.example.in28Minutes.stream_customclass;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

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

        // Creating a comparator and then using it in the sorted method for sorting a List.
        // Note: Comparator.comparing() takes a function that is essentially a key extractor, here we are creating a function that will be the argument to the comparing method.
        Function<String, Integer> strLengthFunc = (str) -> {
            return str.length();
        };
        Comparator<String> stringComparator = Comparator.comparing(strLengthFunc);

        // Sort using comparing method
        Comparator<String> stringComparator1 = Comparator.comparing((String str) -> {
            return str.length();
        });

        System.out.println("Courses before comparing using stringComparator: \n" + courses);
        List<String> sortedCourses = courses.stream()
                .sorted(stringComparator)
                .collect(toList());
        System.out.println("Courses after comparing using stringComparator: \n" + sortedCourses);

//        List<String> sortedCourses2 = courses.stream()
//                .sorted(Comparator.comparing(String::length))
//                .collect(toList());
//        System.out.println("Courses after comparing using stringComparator: \n" + courses);
    }
}
