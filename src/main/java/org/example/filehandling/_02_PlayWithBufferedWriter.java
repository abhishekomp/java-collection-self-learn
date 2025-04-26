package org.example.filehandling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class _02_PlayWithBufferedWriter {
    public static void main(String[] args) {
        // using try with resources
        try(Writer writer = new FileWriter("files/words.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);){

            bufferedWriter.write("Hello World");
            //bufferedWriter.close();
            //bufferedWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
