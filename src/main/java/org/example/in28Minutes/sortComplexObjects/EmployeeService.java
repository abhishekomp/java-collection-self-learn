package org.example.in28Minutes.sortComplexObjects;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class EmployeeService {

    // ── Create a single employee from a request DTO ───────────────────────────
    Employee createNewEmployee(EmployeeRequest employeeRequest) {
        Employee emp = new Employee(employeeRequest.getName(),
                employeeRequest.getDob());
        Employee.Address newAddress = new Employee.Address(
                employeeRequest.getEmployeeStreet(),
                employeeRequest.getEmployeeCity(),
                employeeRequest.getEmployeeZip());
        emp.setAddress(newAddress);
        return emp;
    }

    // ── Create employees from a list of request DTOs ──────────────────────────
    // Fix: stream().forEach() creates a stream unnecessarily; use Iterable.forEach() directly.
    void createNewEmployees(List<EmployeeRequest> employeeRequests) {
        employeeRequests.forEach(this::createNewEmployee);
    }

    static List<Employee> getEmployees() {
        return Employee.employees();
    }

    // ── Sort youngest-first (by year only) ───────────────────────────────────
    // Fix: Integer.parseInt() is preferred over Integer.valueOf() for primitive extraction.
    // Fix: Stream.toList() (Java 16+) replaces the verbose collect(Collectors.toList()).
    List<Employee> sortByYearOfBirth(List<Employee> objects) {
        // Key extractor: parse the 4-digit year from "YYYY-MM-DD"
        Function<Employee, Integer> yearExtractor =
                emp -> Integer.parseInt(emp.getDob().substring(0, 4));

        return objects.stream()
                .sorted(Comparator.comparing(yearExtractor).reversed())
                .toList(); // Java 16+: returns an unmodifiable list
    }

    // ── Sort oldest-first (by year only) ─────────────────────────────────────
    List<Employee> sortByYearOfBirthReversed(List<Employee> objects) {
        Function<Employee, Integer> yearExtractor =
                emp -> Integer.parseInt(emp.getDob().substring(0, 4));

        return objects.stream()
                .sorted(Comparator.comparing(yearExtractor))
                .toList();
    }

    // ── Sort by full date-of-birth, youngest first ────────────────────────────
    // The DOB string format is "YYYY-MM-DD".
    List<Employee> sortByDateOfBirthYoungestFirst(List<Employee> objects) {
        Function<Employee, Integer> yearExtractor  = emp -> Integer.parseInt(emp.getDob().substring(0, 4));
        Function<Employee, Integer> monthExtractor = emp -> Integer.parseInt(emp.getDob().substring(5, 7));
        Function<Employee, Integer> dayExtractor   = emp -> Integer.parseInt(emp.getDob().substring(8));

        return objects.stream()
                .sorted(Comparator.comparing(yearExtractor)
                        .thenComparing(monthExtractor)
                        .thenComparing(dayExtractor)
                        .reversed())
                .toList();
    }

    // ── Sort by full date-of-birth, oldest first ──────────────────────────────
    List<Employee> sortByDateOfBirthOldestFirst(List<Employee> objects) {
        Function<Employee, Integer> yearExtractor  = emp -> Integer.parseInt(emp.getDob().substring(0, 4));
        Function<Employee, Integer> monthExtractor = emp -> Integer.parseInt(emp.getDob().substring(5, 7));
        Function<Employee, Integer> dayExtractor   = emp -> Integer.parseInt(emp.getDob().substring(8));

        return objects.stream()
                .sorted(Comparator.comparing(yearExtractor)
                        .thenComparing(monthExtractor)
                        .thenComparing(dayExtractor))
                .toList();
    }

    // ── Sort by zip code (ascending) ──────────────────────────────────────────
    List<Employee> sortByZipCode(List<Employee> employees) {
        Function<Employee, Employee.Address>  addressExtractor  = Employee::getAddress;
        Function<Employee.Address, Integer>   zipCodeExtractor  =
                addr -> Integer.parseInt(addr.getZipcode());

        return employees.stream()
                .sorted(Comparator.comparing(addressExtractor,
                        Comparator.comparing(zipCodeExtractor)))
                .toList();
    }
}
