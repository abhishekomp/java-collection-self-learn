package org.example.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyIterableImpl<String> implements Iterable<String>{

    private List<String> elements = new ArrayList<>();

    public void add(String element) {
        this.elements.add(element);
    }

    @Override
    public Iterator<String> iterator() {
        return this.elements.iterator();
    }
}
