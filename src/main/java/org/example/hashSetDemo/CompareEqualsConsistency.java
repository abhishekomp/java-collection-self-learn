package org.example.hashSetDemo;

import java.util.Comparator;

public class CompareEqualsConsistency {
    public static void main(String[] args) {

        // Defining a Comparator for String
        Comparator<String> cso = String::compareTo;
        System.out.println(cso.compare("a", "a"));  // => 0
        System.out.println("a".equals("a"));        // => true

        System.out.println("----------------");

        // What Comparator can possibly be inconsistent with equals method
        Comparator<String> cio = String.CASE_INSENSITIVE_ORDER;
        System.out.println(cio.compare("a", "a"));  // => 0
        System.out.println("a".equals("a"));        // => true

        // Right here we have this comparator which is inconsitent with equals method
        System.out.println(cio.compare("a", "A"));  // => 0
        System.out.println("a".equals("A"));        // => false


    }
}
