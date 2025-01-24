package org.example.in28Minutes.sortComplexObjects;

import java.util.List;

public class Employee {

    private final String name;
    private final String dob;
    private Address address;

    public Employee(String name, String dob, Address address) {
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public Employee(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    static class Address {
        private final String street;
        private final String city;
        private final String zipcode;

        Address(String street, String city, String zipcode) {
            this.street = street;
            this.city = city;
            this.zipcode = zipcode;
        }

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getZipcode() {
            return zipcode;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", address=" + address +
                '}';
    }

    static List<Employee> employees(){
        Employee emp1 = new Employee("Samuel", "1987-04-20", new Address("JacksonStreet", "Gothenburg", "41001"));
        Employee emp2 = new Employee("Santos", "1986-04-09", new Address("ManchesterStreet", "Gothenburg", "41004"));
        Employee emp3 = new Employee("Matthew", "1972-07-20", new Address("John väg", "Stockholm", "52001"));
        Employee emp4 = new Employee("Martin", "1987-09-29", new Address("John väg", "Stockholm", "52001"));
        Employee emp5 = new Employee("Martin", "1987-02-12", new Address("Rydersgatan 4", "Stockholm", "11001"));
        Employee emp6 = new Employee("Silvia", "1987-04-22", new Address("Fabriksgatan 5 ", "Stockholm", "11005"));
        return List.of(emp1, emp2, emp3, emp4, emp5, emp6);
    }

    /*
        Employee emp1 = new Employee("Samuel", "1987-04-20", new Address("JacksonStreet", "Gothenburg", "41001"));
        Employee emp2 = new Employee("Santos", "1986-04-09", new Address("ManchesterStreet", "Gothenburg", "41004"));
        Employee emp3 = new Employee("Sonja", "1990-10-26", new Address("Lillakullagatan", "Stockholm", "55001"));
        Employee emp4 = new Employee("Matthew", "1972-07-20", new Address("John väg", "Stockholm", "52001"));
        Employee emp5 = new Employee("Martin", "1987-09-29", new Address("John väg", "Stockholm", "52001"));
        Employee emp6 = new Employee("Natalie", "1971-11-12", new Address("Vasaplatsen", "Gothenburg", "35001"));
        Employee emp7 = new Employee("Martin", "1987-02-12", new Address("Rydersgatan 4", "Stockholm", "11001"));
        Employee emp8 = new Employee("Silvia", "1987-04-22", new Address("Fabriksgatan 5 ", "Stockholm", "11005"));
     */
}
