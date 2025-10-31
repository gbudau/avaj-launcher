package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.aircraft.Flyable;
import ro.academyplus.avaj.exception.ScenarioParseException;
import ro.academyplus.avaj.reporter.Reporter;
import ro.academyplus.avaj.scenario.Scenario;
import ro.academyplus.avaj.scenario.ScenarioParser;
import ro.academyplus.avaj.tower.WeatherTower;

import java.nio.file.Path;

public class Simulator {
    void main(String[] args) {
        if  (args.length != 1) {
            System.out.println("Usage: java " + this.getClass().getName() + " <scenario file>");
            System.exit(1);
        }

        try {
            Path scenarioPath = Path.of(args[0]);
            ScenarioParser parser = new ScenarioParser();
            Scenario scenario = parser.parse(scenarioPath);
            WeatherTower weatherTower = new WeatherTower();
            Reporter.initialize();
            for (Flyable aircraft : scenario.aircraftsList()) {
                aircraft.registerTower(weatherTower);
            }
            for (int i = 0; i < scenario.simulationsCount(); i++) {
                weatherTower.changeWeather();
            }
        } catch (ScenarioParseException e) {
            System.out.println("Error parsing scenario file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        } finally {
            Reporter.close();
        }
    }
}
