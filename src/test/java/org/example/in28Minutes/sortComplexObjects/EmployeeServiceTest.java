package org.example.in28Minutes.sortComplexObjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        employees.forEach(System.out::println);
        List<Employee> sorted = employeeService.sortByYearOfBirth(employees);
        System.out.println("After sorting");
        sorted.forEach(System.out::println);
    }

    @Test
    void sortByYearOfBirthReversed() {
        System.out.println("Before sorting(reversed) using birth year");
        employees.forEach(System.out::println);
        List<Employee> sorted = employeeService.sortByYearOfBirthReversed(employees);
        System.out.println("After sorting(reversed) using birth year");
        sorted.forEach(System.out::println);
    }

    @Test
    void sortByDateOfBirthYoungestFirst() {
        System.out.println("Before sorting using dob(youngest first)");
        employees.forEach(System.out::println);
        List<Employee> sorted = employeeService.sortByDateOfBirthYoungestFirst(employees);
        System.out.println("After sorting using dob(youngest first)");
        sorted.forEach(System.out::println);
    }

    @Test
    void sortByDateOfBirthOldestFirst() {
        System.out.println("Before sorting using dob(oldest first)");
        employees.forEach(System.out::println);
        List<Employee> sorted = employeeService.sortByDateOfBirthOldestFirst(employees);
        System.out.println("After sorting using dob(oldest first)");
        sorted.forEach(System.out::println);

        // JDK 16+: Stream.toList() returns an unmodifiable list — use .stream().map() to transform
        List<String> collectedDobs = sorted.stream()
                .map(Employee::getDob)
                .toList();

        assertIterableEquals(
                List.of("1972-07-20", "1986-04-09", "1987-02-12", "1987-04-20", "1987-04-22", "1987-09-29"),
                collectedDobs);
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