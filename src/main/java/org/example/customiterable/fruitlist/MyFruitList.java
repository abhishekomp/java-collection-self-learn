package org.example.customiterable.fruitlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyFruitList<T> implements Iterable<T>{

    private final List<T> myList;

    public MyFruitList() {
        myList = new ArrayList<T>();
    }

    public void add(T item){
        myList.add(item);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyCustomIterator<T>(myList);
    }

    public class MyCustomIterator<E> implements Iterator<E> {

        int indexPosition = 0;
        private List<E> internalList = new ArrayList<E>();

        private MyCustomIterator(List<E> internalList) {
            this.internalList = internalList;
        }

        @Override
        public boolean hasNext() {
            if(internalList.size() >= indexPosition + 1) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            E val = internalList.get(indexPosition);
            indexPosition++;
            return val;
        }
    }
}
