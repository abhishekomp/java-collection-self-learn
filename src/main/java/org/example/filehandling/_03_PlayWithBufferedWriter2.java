package org.example.filehandling;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class _03_PlayWithBufferedWriter2 {
    public static void main(String[] args) {
        // using try with resources
        // creating the BufferedWriter using Files.newBufferedWriter
        Path path = Path.of("files/words.txt");
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path);){

            bufferedWriter.write("Demonstrating Files.newBufferedWriter(\"files/words.txt\")");
            bufferedWriter.newLine();
            bufferedWriter.append("Appending more text");
            //bufferedWriter.close();
            //bufferedWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
