package org.example.customiterable.carlist;

import java.util.Iterator;

public class MyCarInventory {
    public static void main(String[] args) {
        MyCarList<String> myCarList = new MyCarList<>();
        myCarList.add("Tesla");
        myCarList.add("Audi");
        myCarList.add("McLaren");
        myCarList.add("Mercedes");
        myCarList.add("BMW");
        myCarList.add("Bombardier");
        myCarList.add("Hulk");

        for(String car: myCarList){
            System.out.println(car);
        }
        System.out.println("********Printing using Custom Iterator************");
        Iterator<String> iterator = myCarList.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("********Printing using forEach************");
        myCarList.forEach(car -> System.out.println(car));

    }
}
