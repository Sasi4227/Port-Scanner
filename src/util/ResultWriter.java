package util;

import java.io.FileWriter;
import java.io.IOException;

public class ResultWriter {

    public static synchronized void write(String file, String data) {

        if (file == null) return;

        try {

            FileWriter writer = new FileWriter(file, true);
            writer.write(data + "\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }
    }
}