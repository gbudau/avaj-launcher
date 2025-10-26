package ro.academyplus.avaj.simulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Stream;
import ro.academyplus.avaj.aircraft.Aircraft;
import ro.academyplus.avaj.aircraft.Flyable;
import ro.academyplus.avaj.factory.AircraftFactory;
import ro.academyplus.avaj.model.Coordinates;
import ro.academyplus.avaj.tower.WeatherTower;

public class Simulator {
    void main(String[] args) {
        if  (args.length != 1) {
            System.out.println("Usage: java " + this.getClass().getName() + " <scenario file>");
            System.exit(1);
        }

        Path scenario = Path.of(args[0]);

        int simulationsCount = 0;
        List<Flyable> aircrafts = new ArrayList<>();

        try (Stream<String> lines = Files.lines(scenario.getAbsolutePath())) {
            Iterator<String> iterator = lines.iterator();
            if (!iterator.hasNext()) {
                System.out.println("Scenario file is empty.");
                System.exit(1);
            }
            String simulationsCountStr = iterator.next();
            simulationsCount = parseSimulationsCount(simulationsCountStr);
            if (!isValidSimulationsCount(simulationsCount)) {
                System.out.println("Invalid number of simulations: '" + simulationsCount + "'");
                System.exit(1);
            }
            while (iterator.hasNext()) {
                String line = iterator.next();
                Flyable aircraft = parseAircraft(line);
                aircrafts.add(aircraft);
            }
        } catch (IOException e) {
            System.out.println("Unable to read scenario: " + e.getMessage());
            System.exit(1);
        }

        if (aircrafts.isEmpty()) {
            System.out.println("No aircrafts found in scenario.");
            System.exit(1);
        }
        WeatherTower weatherTower = new WeatherTower();
        for (Flyable aircraft : aircrafts) {
            aircraft.registerTower(weatherTower);
        }
        for (int i = 0; i < simulationsCount; i++) {
            weatherTower.changeWeather();
        }
    }

    private int parseSimulationsCount(String firstLine) {
        int simulationsCount = 0;
        try {
            simulationsCount = Integer.parseInt(firstLine.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for simulations count: '" + firstLine + "'");
            System.exit(1);
        }
        return simulationsCount;
    }

    private Flyable parseAircraft(String line) {
        line = line.trim();
        if (line.isEmpty()) {
            System.out.println("Invalid aircraft description provided.");
            System.exit(1);
        }
        String[] parts = line.split("\\s+");
        if (parts.length != 5) {
            System.out.println("Invalid aircraft description: '" + line +"'");
            System.exit(1);
        }
        String type = parts[0];
        if (!isValidAircraftType(type)) {
            System.out.println("Invalid aircraft type: '" + type + "'");
            System.exit(1);
        }
        String name = parts[1];
        int longitude = parseLongitude(parts[2]);
        if (!isValidCoordinate(longitude)) {
            System.out.println("Invalid longitude: '" + longitude + "'");
            System.exit(1);
        }
        int latitude = parseLatitude(parts[3]);
        if (!isValidCoordinate(latitude)) {
            System.out.println("Invalid latitude: '" + latitude + "'");
            System.exit(1);
        }
        int height = parseHeight(parts[4]);
        if (!isValidHeight(height)) {
            System.out.println("Invalid height: '" + height + "'");
            System.exit(1);
        }

        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        Flyable aircraft = AircraftFactory.newAircraft(type, name, coordinates);
        if (aircraft == null) {
            System.out.println("Unknown aircraft type: '" + type + "'");
            System.exit(1);
        }
        return aircraft;
    }

    private int parseLongitude(String longitudeStr) {
        int longitude = 0;
        try {
            longitude = Integer.parseInt(longitudeStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for longitude: '" + longitudeStr + "'");
            System.exit(1);
        }
        return longitude;
    }

    private int parseLatitude(String latitudeStr) {
        int latitude = 0;
        try {
            latitude = Integer.parseInt(latitudeStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for latitude: '" + latitudeStr + "'");
            System.exit(1);
        }
        return latitude;
    }

    private int parseHeight(String heightStr) {
        int height = 0;
        try {
            height = Integer.parseInt(heightStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for height: '" + heightStr + "'");
            System.exit(1);
        }
        return height;
    }

    private boolean isValidAircraftType(String aircraftType) {
        return switch (aircraftType) {
            case "Helicopter", "JetPlane", "Balloon" -> true;
            default -> false;
        };
    }

    private boolean isValidSimulationsCount(int count) {
        return count > 0;
    }

    private boolean isValidCoordinate(int coordinate) {
        return coordinate > 0;
    }

    private boolean isValidHeight(int height) {
        return height >= 0;
    }
}
