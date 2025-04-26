package org.example.filehandling;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class _01_PlayWithReader {

    public static void main(String[] args) {

        try(Reader reader = new FileReader("files/sonnet.txt");) {
            char[] buf = new char[16];
            int read = reader.read(buf);
            StringBuilder sb = new StringBuilder();
            while (read != -1) {
                sb.append(buf, 0, read);
                read = reader.read(buf);
            }
            System.out.println("sb = \n" + sb);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
