package org.example;

import java.util.List;
import java.util.Objects;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() && getName().equals(person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static List<Person> createPeople() {
        return List.of(
                new Person("Jack", 15),
                new Person("Sara", 20),
                new Person("Bob", 20),
                new Person("Paula", 35),
                new Person("Nancy", 40),
                new Person("Bill", 25),
                new Person("Jill", 50),
                new Person("Tom", 60),
                new Person("Tom", 70)
        );
    }
}
