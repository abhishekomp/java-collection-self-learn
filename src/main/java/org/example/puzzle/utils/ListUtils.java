package org.example.puzzle.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

public class ListUtils {

    public static <T> List<T> reverse(final List<T> sourceList) {
        int size = sourceList.size() - 1 ;
        return IntStream.rangeClosed(0, size)
                .map(i -> size - i)
                .mapToObj(sourceList::get)
                .collect(toCollection(ArrayList::new));
    }

}
