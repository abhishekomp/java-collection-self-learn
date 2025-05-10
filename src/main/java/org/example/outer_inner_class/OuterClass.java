package org.example.outer_inner_class;

public class OuterClass {
    private String outerVariable = "I am an outer class variable";

    // Inner class definition
    class InnerClass {

        // Inner class can have its own variables
        public String innerVariable = "I am an inner class variable";

        // Inner class can access outer class variables
        public void printOuterVariable() {
            // Accessing outer class variable directly
            System.out.println(outerVariable);

            // `OuterClass.this` refers to the OuterClass instance
            System.out.println("Outer variable: " + OuterClass.this.outerVariable);
        }

        public void printInnerVariable() {
            // Accessing inner class variable directly
            System.out.println(innerVariable);
        }
    }

    public static void main(String[] args) {
        // Create an instance of the outer class
        OuterClass outer = new OuterClass();

        // Create an instance of the inner class
        OuterClass.InnerClass inner = outer.new InnerClass();

        // Call the method that accesses the outer class variable
        inner.printOuterVariable();
    }
}

/*
    * Key Points:
    Implicit Reference: The inner class has an implicit reference to the instance of the outer class, allowing it to access its members (variables and methods).
    Access Modifiers: The access level of the outer class variable does not restrict the inner class from accessing it, even if it is private. This is because the inner class is part of the outer class.
    Instantiation: To create an instance of the inner class, you must first have an instance of the outer class. Then, use the outer class object to instantiate the inner class using outer.new InnerClass() syntax.
    This approach makes it convenient to use inner classes when they need direct access to their enclosing class's data.
 */

/*
Key Points about using this keyword in Inner Classes:
    * this Keyword in Inner Class: In InnerClass, using this.innerVariable accesses the instance variables of the inner class.
    * Referencing Outer Class: OuterClass.this is used to reference the outer class instance. This allows access to the outer class's members even when there's a variable name conflict between the inner and outer classes.
    * Resolution: This approach helps in disambiguating which class’s instance variable or method you are referring to when both the outer and inner classes have similar members.
 */


/*
 * This code demonstrates the use of inner classes in Java. An inner class is a class defined within another class. Inner classes can access the members (variables and methods) of the outer class, even if they are private.
 *
 * The `OuterClass` contains an inner class called `InnerClass`. The inner class can access the outer class's variables and methods directly. The `main` method creates an instance of the outer class and then creates an instance of the inner class to demonstrate this access.
 */


/*
    * In this example, the `OuterClass` contains an inner class called `InnerClass`. The inner class can access the outer class's variables and methods directly. The `main` method creates an instance of the outer class and then creates an instance of the inner class to demonstrate this access.
    * The inner class can also have its own variables and methods. In this case, the `InnerClass` has a method called `printInnerVariable` that prints the value of its own variable.
    * The `main` method of the `OuterClassClient` class demonstrates how to create an instance of the inner class and call its methods.
    * The inner class can be used to logically group classes that are only used in one place, which increases encapsulation and helps to keep the code organized.
    * The inner class can also be used to implement the "Decorator" pattern, where the inner class adds additional functionality to the outer class without modifying its structure.
    * The inner class can access the outer class's private members, which allows for tighter coupling between the two classes.
    * The inner class can be used to create a more readable and maintainable code structure, especially when the inner class is closely related to the outer class.
    * The inner class can be used to implement the "Builder" pattern, where the inner class is used to construct instances of the outer class with a fluent interface.
    * The inner class can be used to implement the "Factory" pattern, where the inner class is used to create instances of the outer class with specific configurations.
    * The inner class can be used to implement the "Adapter" pattern, where the inner class is used to adapt the interface of the outer class to a different interface.
    * The inner class can be used to implement the "Proxy" pattern, where the inner class is used to control access to the outer class's methods and variables.
    * The inner class can be used to implement the "Strategy" pattern, where the inner class is used to encapsulate a specific algorithm or behavior that can be used by the outer class.
    * The inner class can be used to implement the "Observer" pattern, where the inner class is used to notify the outer class of changes in its state.
    * The inner class can be used to implement the "Command" pattern, where the inner class is used to encapsulate a request or action that can be executed by the outer class.
 */