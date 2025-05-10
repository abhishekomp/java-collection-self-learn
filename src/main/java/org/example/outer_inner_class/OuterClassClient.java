package org.example.outer_inner_class;

public class OuterClassClient {

    public static void main(String[] args) {

        // Create an instance of the outer class
        OuterClass outer = new OuterClass();

        // Create an instance of the inner class
        OuterClass.InnerClass inner = outer.new InnerClass();

        // Call the method that accesses the outer class variable
        inner.printOuterVariable();

        // Call the method that accesses the inner class variable
        inner.printInnerVariable();

        // Accessing the inner class variable directly
        System.out.println(inner.innerVariable);
    }
}
