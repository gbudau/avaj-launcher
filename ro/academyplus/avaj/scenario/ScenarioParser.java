package ro.academyplus.avaj.scenario;

import ro.academyplus.avaj.aircraft.Flyable;
import ro.academyplus.avaj.exception.InvalidAircraftException;
import ro.academyplus.avaj.exception.InvalidCoordinateException;
import ro.academyplus.avaj.exception.ScenarioParseException;
import ro.academyplus.avaj.factory.AircraftFactory;
import ro.academyplus.avaj.model.Coordinates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ScenarioParser {

    public Scenario parse(Path filePath) throws ScenarioParseException {
        try (Stream<String> lines = Files.lines(filePath)) {
            Iterator<String> iterator = lines.iterator();

            if (!iterator.hasNext()) {
                throw new ScenarioParseException("Scenario file is empty.");
            }

            int simulationsCount = parseSimulationsCount(iterator.next());
            List<Flyable> aircraftsList = parseAircrafts(iterator);

            return new Scenario(simulationsCount, aircraftsList);

        } catch (IOException e) {
            throw new ScenarioParseException("Unable to read scenario file: " + e.getMessage(), e);
        }
    }

    private int parseSimulationsCount(String firstLine) throws ScenarioParseException {
        int count = parseInteger(firstLine, "simulations count");
        if (count <= 0) {
            throw new ScenarioParseException("Invalid number of simulations: " + count + ". Must be greater than 0.");
        }
        return count;
    }

    private List<Flyable> parseAircrafts(Iterator<String> iterator) throws ScenarioParseException {
        List<Flyable> aircraftsList = new ArrayList<>();
        int lineNumber = 2;

        while (iterator.hasNext()) {
            String line = iterator.next().trim();

            try {
                aircraftsList.add(parseAircraft(line));
            } catch (ScenarioParseException | InvalidAircraftException e) {
                throw new ScenarioParseException("Error on line " + lineNumber + ": " + e.getMessage(), e);
            }
            lineNumber++;
        }

        if (aircraftsList.isEmpty()) {
            throw new ScenarioParseException("No aircraft defined in the scenario.");
        }

        return aircraftsList;
    }

    private Flyable parseAircraft(String line) throws ScenarioParseException, InvalidAircraftException {
        String[] parts = line.split(" ");
        if (parts.length != 5) {
            throw new ScenarioParseException("Invalid line: "  + "'" + line + "'");
        }

        String type = parts[0];
        String name = parts[1];

        int longitude = parseInteger(parts[2], "longitude");
        int latitude = parseInteger(parts[3], "latitude");
        int height = parseInteger(parts[4], "height");

        Coordinates coordinates;
        try {
            coordinates = new Coordinates(longitude, latitude, height);
        } catch (InvalidCoordinateException e) {
            throw new ScenarioParseException("Invalid coordinates for " + name + ": " + e.getMessage());
        }

        return AircraftFactory.newAircraft(type, name, coordinates);
    }

    private int parseInteger(String str, String fieldName) throws ScenarioParseException {
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException _) {
            throw new ScenarioParseException("Invalid number format for " + fieldName + ": '" + str + "'");
        }
    }
}