/*
 * This code is part of the course "Working with Streams and Lambda Expressions in Java (Java SE 11 Developer Certification 1Z0-819)" for Pluralsight.
 *
 * Copyright (C) 2021 by Jesper de Jong (jesper@jdj-it.com).
 */
package org.example.workingWithStreamPS._02.examples;


import org.example.workingWithStreamPS._02.ExampleData;
import org.example.workingWithStreamPS._02.Product;

import java.util.Comparator;
import java.util.List;

public class LambdasExample01 {

    public static void main(String[] args) {
        List<Product> products = ExampleData.getProducts();

//        products.sort(new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                return o1.getPrice().compareTo(o2.getPrice());
//            }
//        });

        Comparator<Product> productComparator = (p1, p2) -> p1.getPrice().compareTo(p2.getPrice());
        products.sort(productComparator);

        for (Product product : products) {
            System.out.println(product);
        }
    }
}
