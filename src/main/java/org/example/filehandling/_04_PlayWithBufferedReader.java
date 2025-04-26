package org.example.filehandling;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class _04_PlayWithBufferedReader {

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("files/sonnet.txt"));) {
            String line = bufferedReader.readLine();

            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
