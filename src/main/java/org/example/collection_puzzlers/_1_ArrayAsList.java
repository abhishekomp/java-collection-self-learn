package org.example.collection_puzzlers;

import java.util.Arrays;
import java.util.List;

public class _1_ArrayAsList {
    public static void main(String[] args) {

        String[] stringArray = {"one", "two", "three"};
        List<String> stringList = Arrays.asList(stringArray);

//         Cannot add new items to this list returned by Arrays.asList()
//         Cannot remove element from the list returned by Arrays.asList()
//         Note: But possible to set the element by using set() method on the returned list reference.
//        stringList.add("four");
//        List<String> stringList1 = List.of(stringArray);
//        stringList1.add("four");
//        System.out.println(stringList1);

        int[] intArray = {1, 2, 3};
        // It will be converted into a List of singly array
        var intList = Arrays.asList(intArray);  //Be aware that an array of primitive ints will not be converted to a List of Integers when using Arrays.asList()

        System.out.print(stringList.contains("one") + ", ");
        System.out.println(intList.contains(1));

        //true, false
    }

}
