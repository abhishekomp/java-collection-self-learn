package org.example.collection_puzzlers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class _3a_NullInList {
    public static void main(String[] args) {
        String[] ints = {"a", "b", "c", null};

        // this takes null as one of the elements in the stream
        List<String> strings1 = Stream.of(ints).collect(toList());
        //System.out.println("strings1 = " + strings1);
        strings1.removeIf(Objects::isNull);
        System.out.print(strings1.size());  // 4

        List<String> strings3 = Stream.of(ints).collect(toList());
        strings3.removeIf(Objects::isNull);
        System.out.print(strings3.size());  // 3


//        List<String> strings2 = List.of(ints);
//        System.out.print(strings2.size());
        //Exception in thread "main" java.lang.NullPointerException



        String[] strings = {"a", "b", "c", "de"};
        List<String> stringList = List.of(strings); //We cannot put a null in a list created by List.of()
        System.out.println("stringList = " + stringList);
        stringList.removeIf(e -> e.length() > 1);   //java.lang.UnsupportedOperationException because the list is immutable
        System.out.println("stringList = " + stringList);

    }
}
