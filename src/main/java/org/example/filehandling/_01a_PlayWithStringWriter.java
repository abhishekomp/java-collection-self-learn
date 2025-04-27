package org.example.filehandling;

import java.io.IOException;
import java.io.StringWriter;

public class _01a_PlayWithStringWriter {

    public static void main(String[] args) {

        try(StringWriter writer = new StringWriter()){

            writer.write("Hello World");
            writer.flush();

            System.out.println((writer.getBuffer()));
            System.out.println(writer.toString());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
