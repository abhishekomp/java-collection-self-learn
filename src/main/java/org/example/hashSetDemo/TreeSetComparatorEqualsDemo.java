package org.example.hashSetDemo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetComparatorEqualsDemo {
    public static void main(String[] args) {

        // Defining a TreeSet
        TreeSet<String> tree = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        tree.addAll(List.of("MMM", "aaa", "zzz"));
        System.out.println("treeSet(case insensitive):  " + tree);   // => [aaa, MMM, zzz]

        System.out.println(tree.contains("MMM"));   //true
        System.out.println(tree.contains("mmm"));   //true

        System.out.println("--------------");

        Set<String> hashSet = new HashSet<>(tree);
        System.out.println("hashSet:                    " + hashSet);
        System.out.println(hashSet.contains("MMM"));    //true
        System.out.println(hashSet.contains("mmm"));    //false

        // Comparing sets for equality. The notion of equality of sets is:
        // If 2 sets are equal then they must have the same number of elements and if every element of that set is also present in this set.
        System.out.println("Is hasSet equal to treeSet: " + hashSet.equals(tree));  //false
        System.out.println("Is treeSet equal to hashSet: " + tree.equals(hashSet)); //true

        System.out.println("--------------");

        //Now we will change the hashSet and put mmm instead of MMM
        hashSet.remove("MMM");
        hashSet.add("mmm");
        System.out.println("hashSet:                    " + hashSet);
        System.out.println("treeSet(case insensitive):  " + tree);

        System.out.println("Is hasSet equal to treeSet: " + hashSet.equals(tree));  // Is every element of treeSet present in hashSet
        System.out.println("Is treeSet equal to hashSet: " + tree.equals(hashSet)); // Is every element of hashSet present in treeSet
    }
}
