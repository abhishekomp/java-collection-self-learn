package org.example.in28Minutes.sortComplexObjects;

public class EmployeeRequest {

    private final String name;
    private final String dob;
    private final String employeeStreet;
    private final String employeeCity;
    private final String employeeZip;


    public EmployeeRequest(String name, String dob, String employeeStreet, String employeeCity, String employeeZip) {
        this.name = name;
        this.dob = dob;
        this.employeeStreet = employeeStreet;
        this.employeeCity = employeeCity;
        this.employeeZip = employeeZip;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmployeeStreet() {
        return employeeStreet;
    }

    public String getEmployeeCity() {
        return employeeCity;
    }

    public String getEmployeeZip() {
        return employeeZip;
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", employeeStreet='" + employeeStreet + '\'' +
                ", employeeCity='" + employeeCity + '\'' +
                ", employeeZip='" + employeeZip + '\'' +
                '}';
    }
}
