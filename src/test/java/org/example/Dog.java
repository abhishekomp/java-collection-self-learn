package org.example;

import java.util.List;
import java.util.Objects;

public class Dog {
    private final String name;
    private final String breed;
    private final int age;

    public Dog(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return getAge() == dog.getAge() && getName().equals(dog.getName()) && getBreed().equals(dog.getBreed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBreed(), getAge());
    }

    public static List<Dog> getDogs(){
        return List.of(
                new Dog("dog1", "German Shepard", 5),
                new Dog("dog2", "Akita Ilu", 10),
                new Dog("dog3", "Akita Ilu", 5),
                new Dog("dog4", "Golden Retriever", 15),
                new Dog("dog5", "Beagle", 8),
                new Dog("dog6", "Beagle", 8)
        );
    }
}
