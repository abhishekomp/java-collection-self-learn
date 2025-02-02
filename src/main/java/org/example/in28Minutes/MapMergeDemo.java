package org.example.in28Minutes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : abhishekomprakash
 * @since : Fri, 2025-Jan-17
 * Created with IntelliJ IDEA
 */

public class MapMergeDemo {
    public static void main(String[] args) {
        Map<String, Employee> map1 = new HashMap<>();
        Map<String, Employee> map2 = new HashMap<>();

        Employee employee1 = new Employee(1L, "Henry");
        map1.put(employee1.getName(), employee1);
        Employee employee2 = new Employee(22L, "Annie");
        map1.put(employee2.getName(), employee2);
        Employee employee3 = new Employee(8L, "John");
        map1.put(employee3.getName(), employee3);

        Employee employee4 = new Employee(2L, "George");
        map2.put(employee4.getName(), employee4);
        Employee employee5 = new Employee(3L, "Henry");
        map2.put(employee5.getName(), employee5);

        Map<String, Employee> map3 = new HashMap<>(map1);
        //map3.merge(key, value, (v1, v2) -> new Employee(v1.getId(), v2.getName()));
        System.out.println("->->->->-> Map 1 ->->->->->->->");
        map1.forEach((k, v) -> System.out.println(k + "->" + v));

        System.out.println("->->->->-> Map 2 ->->->->->->->");
        map2.forEach((k, v) -> System.out.println(k + "->" + v));

        System.out.println("->->->->-> Merging map 2 to map 1(map3) to get a resultant map ->->->->->->->");

        map2.forEach((key, value) ->
                map3.merge(key, value, (v1, v2) -> new Employee(v1.getId(), v2.getName())));

        System.out.println("After merging map2 into map3, map3 is now: ");
        map3.forEach((k, v) -> System.out.println(k + "->" + v));
    }
}

class Employee {

    private Long id;
    private String name;

    // constructor, getters, setters

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
