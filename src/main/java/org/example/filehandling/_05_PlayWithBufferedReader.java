package org.example.filehandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class _05_PlayWithBufferedReader {

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("files/sonnet.txt"));) {

            bufferedReader.lines().forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
