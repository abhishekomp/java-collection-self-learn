package org.example.in28Minutes;

import java.util.List;

public class MethodReferenceDemo {

    public static void main(String[] args) {

        List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices","AWS", "PCF","Azure", "Docker", "Kubernetes");
        courses.stream()
                .map(course -> course.toUpperCase())
                .forEach(System.out::println);


        System.out.println("Now calling toUpperCase method(this is an instance method) of String class using method reference");

        courses.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

    }
}
