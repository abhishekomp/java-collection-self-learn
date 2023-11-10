package org.example;

import java.util.List;
import java.util.Objects;

public class Student {
    private final String firstName;
    private final String lastName;
    private final char grade;
    private final int score;

    public Student(String firstName, String lastName, char grade, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.score = score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        //return getGrade() == student.getGrade() && getScore() == student.getScore() && getFirstName().equals(student.getFirstName()) && Objects.equals(getLastName(), student.getLastName());
        return getGrade() == student.getGrade() && getScore() == student.getScore() && Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getLastName(), student.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getGrade(), getScore());
    }

    public static List<Student> getStudents(){
        return List.of(
                new Student("John", "Blue", 'A', 90),
                new Student("Johan", "", 'A', 80),
                new Student("Elsa", "Marin", 'B', 70),
                new Student("Emma", "Martisson", 'C', 60),
                new Student("John", "Jani", 'A', 95),
                new Student("Michael", "Jordan", 'A', 98)
        );
    }
}
