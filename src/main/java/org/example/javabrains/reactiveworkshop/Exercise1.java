package org.example.javabrains.reactiveworkshop;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author : abhishekomprakash
 * @since : Mon, 2025-Jan-06
 * Created with IntelliJ IDEA
 */

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        // TODO: Write code here
        StreamSource.intNumbersStream().forEach(System.out::println);
        System.out.println("************************");

        // Print numbers from intNumbersStream that are less than 5
        // TODO: Write code here
        StreamSource.intNumbersStream()
                .filter(num -> num < 5)
                .forEach(System.out::println);
        System.out.println("************************");

        // Print the second and third numbers in intNumbersStream that's greater than 5
        // TODO: Write code here
        StreamSource.intNumbersStream()
                .filter(num -> num > 5)
                .skip(1)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("************************");

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        // TODO: Write code here
        System.out.println(
            StreamSource.intNumbersStream()
                .filter(num -> num > 5)
                .findFirst()
                .orElse(-1)
        );
        System.out.println("************************");

        // Print first names of all users in userStream
        // TODO: Write code here
        StreamSource.userStream()
                .map(User::getFirstName)
                .forEach(System.out::println);
        System.out.println("************************");


        // Print first names in userStream for users that have IDs from number stream
        // TODO: Write code here
        StreamSource.userStream()
                .filter(user -> StreamSource.intNumbersStream().anyMatch(id -> id == user.getId()))
                .forEach(user -> System.out.println(user.getFirstName()));
        System.out.println("************************");

        // Another way
        System.out.println("Another way");
        StreamSource.intNumbersStream()
                .flatMap(num -> StreamSource.userStream()
                        .filter(user -> num == user.getId()))
                .forEach(user -> System.out.println(user.getFirstName()));
        System.out.println("************************");

        // Yet Another way to fetch only one record from the user list if there exists multiple entries with matching id
        System.out.println("Yet Another way");
        StreamSource.intNumbersStream()
                .map(id -> StreamSource.userStream()
                        .filter(user -> id == user.getId()).findFirst())
                .filter(Optional::isPresent)
                .forEach(op -> System.out.println(op.get().getFirstName()));
        System.out.println("************************");
        //.map(op -> op.get().getFirstName())
    }
}
