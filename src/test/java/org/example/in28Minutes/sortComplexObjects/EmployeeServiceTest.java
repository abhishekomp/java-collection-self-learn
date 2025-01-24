package org.example.in28Minutes.sortComplexObjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class EmployeeServiceTest {

    EmployeeService employeeService;
    List<Employee> employees;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
        employees = EmployeeService.getEmployees();
    }

    @Test
    void sortByYearOfBirth() {
        System.out.println("Before sorting");
        employees.stream().forEach(System.out::println);
        List<Employee> sorted = employeeService.sortByYearOfBirth(employees);
        System.out.println("After sorting");
        sorted.stream().forEach(System.out::println);
    }

    @Test
    void sortByYearOfBirthReversed() {
        System.out.println("Before sorting(reversed) using birth year");
        employees.stream().forEach(System.out::println);
        List<Employee> sorted = employeeService.sortByYearOfBirthReversed(employees);
        System.out.println("After sorting(reversed) using birth year");
        sorted.stream().forEach(System.out::println);
    }

    @Test
    void sortByDateOfBirthYoungestFirst() {
        System.out.println("Before sorting using dob(youngest first)");
        employees.stream().forEach(System.out::println);
        List<Employee> sorted = employeeService.sortByDateOfBirthYoungestFirst(employees);
        System.out.println("After sorting using dob(youngest first)");
        sorted.stream().forEach(System.out::println);
    }

    @Test
    void sortByDateOfBirthOldestFirst() {
        System.out.println("Before sorting using dob(oldest first)");
        employees.stream().forEach(System.out::println);
        List<Employee> sorted = employeeService.sortByDateOfBirthOldestFirst(employees);
        System.out.println("After sorting using dob(oldest first)");
        sorted.stream().forEach(System.out::println);
        List<String> sortedDobs = sorted.stream().map(Employee::getDob).collect(toList());
//        String quotedDobs = sorted.stream().map(Employee::getDob)
//                .map(dob -> "\"" + dob + "\"")
//                .collect(joining(","));

        List<String> collectedDobs = sorted.stream().map(Employee::getDob)
                .collect(toList());
        //List.of("1972-07-20","1986-04-09","1987-02-12","1987-04-20","1987-04-22","1987-09-29");

        assertIterableEquals(collectedDobs, List.of("1972-07-20","1986-04-09","1987-02-12","1987-04-20","1987-04-22","1987-09-29"));
        //assertIterableEquals(collectedDobs, List.of("1972-07-20","1987-02-12", "1986-04-09","1987-04-20","1987-04-22","1987-09-29"));
    }

    @Test
    void sortByZipCode() {
        System.out.println("Before sorting using zipCode");
        employees.forEach(System.out::println);
        List<Employee> sorted = employeeService.sortByZipCode(employees);
        System.out.println("After sorting using zipCode");
        sorted.forEach(System.out::println);
    }
}