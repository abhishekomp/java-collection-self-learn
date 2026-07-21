package org.example.iterable.custom_IntCollection;

import java.util.Iterator;

public class IntegerCollection implements Iterable<Integer>{

    private int[] elements = {1, 2, 3, 4, 5};
    private int size;

    @Override
    public Iterator<Integer> iterator() {
        return new IntegerIterator(elements);
    }


    private class IntegerIterator implements Iterator<Integer> {
        private int currentIndex = 0;
        private int[] elements;
        private int size;

        public IntegerIterator(int[] elements) {
            this.elements = elements;
            this.size = elements.length;
        }


        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements to iterate.");
            }
            return elements[currentIndex++];

        }
    }
}
