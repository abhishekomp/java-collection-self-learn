package org.example.iterable;

import java.util.Iterator;

public class MyIterableUsage {
    public static void main(String[] args) {
        MyIterableImpl<String> strList = new MyIterableImpl<>();
        strList.add("one");
        strList.add("two");
        strList.add("three");

        for(String element : strList){
            System.out.println(element);
        }

        Iterator<String> iterator = strList.iterator();
        while(iterator.hasNext()) {
            System.out.println("string = " + iterator.next());
        }

        strList.forEach(element -> System.out.println(element));

    }


}
