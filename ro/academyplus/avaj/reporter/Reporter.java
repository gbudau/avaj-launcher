package ro.academyplus.avaj.reporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Reporter {

    private static final String REPORT_FILE = "simulation.txt";
    private static PrintWriter writer;

    private Reporter() {}

    public static synchronized void initialize() throws IOException {
        if (writer != null) {
            writer.close();
        }
        writer = new PrintWriter(new BufferedWriter(new FileWriter(REPORT_FILE, false)));
    }

    public static synchronized void report(String message) {
        if (writer == null) {
            System.out.println("Reporter not initialized! Message: " + message);
            return;
        }
        writer.println(message);
    }

    public static synchronized void close() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }
}