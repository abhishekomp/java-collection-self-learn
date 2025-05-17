package org.example.static_nested_class.passenger;

public class PassengerMain {
    public static void main(String[] args) {
        Passenger passenger1 = new Passenger("John Doe");
        Passenger passenger2 = new Passenger("Jane Smith", 5, 10);

        System.out.println("Passenger 1: " + passenger1);
        System.out.println("Passenger 2: " + passenger2);
    }
}
