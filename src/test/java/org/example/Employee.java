package org.example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Employee {
    private final String name;
    private final LocalDate dateOfBirth;
    private final double salary;


    public Employee(String name, LocalDate dateOfBirth, double salary) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.getSalary(), getSalary()) == 0 && getName().equals(employee.getName()) && Objects.equals(getDateOfBirth(), employee.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDateOfBirth(), getSalary());
    }

    public static List<Employee> getEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee("Mike", LocalDate.of(2000, 5, 3), 50000.0),
                new Employee("Sham", LocalDate.of(2000, 11, 10), 45000.0),
                new Employee("Casi", LocalDate.of(2000, 7, 9), 60000.0),
                new Employee("Smith", LocalDate.of(1999, 7, 21), 80000.0)
        );
        return employees;
    }

    public static void main(String[] args) {
        List<Employee> employees = Employee.getEmployees();
        employees.forEach(System.out::println);
    }
}
