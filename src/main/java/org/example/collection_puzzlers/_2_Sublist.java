package org.example.collection_puzzlers;

import java.util.ArrayList;
import java.util.List;

public class _2_Sublist {
    public static void main(String[] args) {

        List<Integer> ints = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        // Sublist will be empty
        List<Integer> subList = ints.subList(0, 0);
        //System.out.println("subList = " + subList);
        System.out.print(subList + " ");

        // Sublist will modify the original list.
        subList.addAll(List.of(10, 11, 12));
        ints.addAll(1, List.of(10, 11, 12));
        System.out.print(ints);

        // [] [10, 11, 12, 1, 2, 3, 4, 5]
    }

}
