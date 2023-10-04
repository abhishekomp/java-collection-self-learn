package org.example.filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateFileDemo {
    public static void main(String[] args) {
        // Code to create a new text file
        // The file is created in the directory where src folder exists. src/main/java/org.example/filehandling/CreateFileDemo.java
        File myFile = new File("cwh111file.txt");
        try {
            myFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create this file");
            e.printStackTrace();
        }

        // Code to write to a text file
        try {
            FileWriter fileWriter = new FileWriter("cwh111file.txt");
            fileWriter.write("This is our first file from this Java course\nOkay now bye");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Reading from a text file
        System.out.println("Reading from a file");
        // The argument can be file name or the path to the file.
        File myFile1 = new File("cwh111file.txt");
        try {
            Scanner sc = new Scanner(myFile1);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Deleting a text file
        File myFile2 = new File("cwh111file.txt");
        if(myFile2.delete()) {
            System.out.println("I have deleted: " + myFile2.getName());
        } else {
            System.out.println("An error happened while deleting the file");
        }

    }
}
