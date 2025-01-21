package org.example.in28Minutes.stream_customclass;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FP04CustomClassSorting {
    public static void main(String[] args) {
        List<Course> courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 18000),
                new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000),
                new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker", "Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000)
        );

        Comparator<Course> compareByNumOfStudentsAsc = Comparator.comparing(Course::getNoOfStudents);
        System.out.println(courses.stream().sorted(compareByNumOfStudentsAsc).collect(toList()));
        //[FullStack:14000:91, Spring Boot:18000:95, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, AWS:21000:92, Azure:21000:99, API:22000:97, Microservices:25000:96]

        // placing reversed() inside or outside makes the difference in the final result. In this case, reversed() is placed outside.
        Comparator<Course> compareByNumOfStudentsDesc = Comparator.comparing(Course::getNoOfStudents).reversed();
        System.out.println(courses.stream().sorted(compareByNumOfStudentsDesc).collect(toList()));
        //[Microservices:25000:96, API:22000:97, AWS:21000:92, Azure:21000:99, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95, FullStack:14000:91]

        Comparator<Course> compareByNumOfStudentsAndReviewScore = Comparator.comparing(Course::getNoOfStudents)
                .thenComparing(Course::getReviewScore);
        System.out.println(courses.stream().sorted(compareByNumOfStudentsAndReviewScore).collect(toList()));

        // placing reversed() inside or outside makes the difference in the final result. In this case, reversed() is placed inside.
        Comparator<Course> compareByNumOfStudentsAndReviewScoreReversed = Comparator.comparing(Course::getNoOfStudents)
                        .thenComparing(Comparator.comparing(Course::getReviewScore).reversed());
        System.out.println(courses.stream().sorted(compareByNumOfStudentsAndReviewScoreReversed).collect(toList()));

    }
}
