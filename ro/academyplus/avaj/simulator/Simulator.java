package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.aircraft.Aircraft;
import ro.academyplus.avaj.aircraft.Flyable;
import ro.academyplus.avaj.exception.ScenarioParseException;
import ro.academyplus.avaj.factory.AircraftFactory;
import ro.academyplus.avaj.model.Coordinates;
import ro.academyplus.avaj.reporter.Reporter;
import ro.academyplus.avaj.scenario.Scenario;
import ro.academyplus.avaj.scenario.ScenarioParser;
import ro.academyplus.avaj.tower.WeatherTower;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Stream;

public class Simulator {
    void main(String[] args) {
        if  (args.length != 1) {
            System.out.println("Usage: java " + this.getClass().getName() + " <scenario file>");
            System.exit(1);
        }

        try {
            Reporter.initialize();
        } catch (Exception e) {
            System.out.println("Could not initialize reporter: " + e.getMessage());
            System.exit(1);
        }

        Scenario scenario;
        try {
            Path scenarioPath = Path.of(args[0]);
            ScenarioParser parser = new ScenarioParser();
            scenario = parser.parse(scenarioPath);
        } catch (ScenarioParseException e) {
            System.out.println("Error parsing scenario file: " + e.getMessage());
            System.exit(1);
            return;
        } catch (Exception e) {
            System.out.println("An unexpected error ocurred: " + e.getMessage());
            System.exit(1);
            return;
        }

        WeatherTower weatherTower = new WeatherTower();
        for (Flyable aircraft : scenario.getAircraftsList()) {
            aircraft.registerTower(weatherTower);
        }
        for (int i = 0; i < scenario.getSimulationsCount(); i++) {
            weatherTower.changeWeather();
        }

        Reporter.close();
    }
}
