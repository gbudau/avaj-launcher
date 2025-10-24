package ro.academyplus.avaj.simulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Simulator {
    void main(String[] args) {
        if  (args.length != 1) {
            System.out.println("Usage: java ro.academyplus.avaj.simulator.Simulator <scenario file>");
            System.exit(1);
        }

        Path scenario = Path.of(args[0]);

        try {
            List<String> lines = Files.readAllLines(scenario.toAbsolutePath());
            System.out.println("Loaded scenario file: " + scenario);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to read scenario: " + e.getMessage());
            System.exit(1);
        }
    }
}
