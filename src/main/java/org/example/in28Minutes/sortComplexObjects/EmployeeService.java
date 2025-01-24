package org.example.in28Minutes.sortComplexObjects;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class EmployeeService {

    // create a single employee
    Employee createNewEmployee(EmployeeRequest employeeRequest){
       Employee emp = new Employee(employeeRequest.getName(),
               employeeRequest.getDob());
       Employee.Address newAddress = new Employee.Address(
                employeeRequest.getEmployeeStreet(),
                employeeRequest.getEmployeeCity(),
                employeeRequest.getEmployeeZip());

       emp.setAddress(newAddress);
       return emp;
    }

    // create employees
    void createNewEmployees(List<EmployeeRequest> employeeRequests){
        employeeRequests.stream()
                .forEach(employeeRequest -> this.createNewEmployee(employeeRequest));
    }

    static List<Employee> getEmployees(){
        return Employee.employees();
    }

    // default sort a list of employees. By default, sort happens using the year of birth. The one who is youngest should be the first in the result.
    List<Employee> sortByYearOfBirth(List<Employee> objects) {
        Function<Employee, Integer> yearExtractor = emp -> Integer.valueOf(emp.getDob().substring(0, 4));

//        Function<Employee, Integer> yearExtractor = emp -> {
//            return Integer.valueOf(emp.getDob().substring(0, 4));
//        };

        return objects.stream()
                .sorted(Comparator.comparing(yearExtractor).reversed())
                .collect(toList());
    }

    // sort a list of employees in the reverse order of their year of birth. the one who is oldest should be the first in the result.
    List<Employee> sortByYearOfBirthReversed(List<Employee> objects) {
        Function<Employee, Integer> yearExtractor = emp -> Integer.valueOf(emp.getDob().substring(0, 4));
        return objects.stream()
                .sorted(Comparator.comparing(yearExtractor))
                .collect(toList());
    }

    // sort employees by date of birth. The one who is youngest should be the first in the result.
    List<Employee> sortByDateOfBirthYoungestFirst(List<Employee> objects) {
        Function<Employee, Integer> yearExtractor = emp -> Integer.valueOf(emp.getDob().substring(0, 4));
        //1985-01-01
        Function<Employee, Integer> monthExtractor = emp -> Integer.valueOf(emp.getDob().substring(5, 7));
        Function<Employee, Integer> dateExtractor = emp -> Integer.valueOf(emp.getDob().substring(8));
        return objects.stream()
                .sorted(Comparator.comparing(yearExtractor)
                                .thenComparing(Comparator.comparing(monthExtractor)
                                                .thenComparing(Comparator.comparing(dateExtractor))
                                        )
                        .reversed())
                .collect(toList());
    }

    // sort employees by date of birth. The one who is oldest should be the first in the result.
    List<Employee> sortByDateOfBirthOldestFirst(List<Employee> objects) {
        Function<Employee, Integer> yearExtractor = emp -> Integer.valueOf(emp.getDob().substring(0, 4));
        //1985-01-01
        Function<Employee, Integer> monthExtractor = emp -> Integer.valueOf(emp.getDob().substring(5, 7));
        Function<Employee, Integer> dateExtractor = emp -> Integer.valueOf(emp.getDob().substring(8));
        return objects.stream()
                .sorted(Comparator.comparing(yearExtractor)
                        .thenComparing(Comparator.comparing(monthExtractor)
                                .thenComparing(Comparator.comparing(dateExtractor))
                        ))
                .collect(toList());
    }
}
