package org.example;

public class EqualsAndDoubleEqualTo {
    public static void main(String[] args) {
        compareTwoArrays();
    }

    private static void compareTwoArrays() {
        int arr1[] = {1, 2, 3};
        int arr2[] = {1, 2, 3};
        if (arr1 == arr2) {
            System.out.println("Same");
        } else {
            System.out.println("Different");
        }
        if (arr1.equals(arr2)) {
            System.out.println("Same");
        } else {
            System.out.println("Not same");
        }
    }
}
