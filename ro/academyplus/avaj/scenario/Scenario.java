package ro.academyplus.avaj.scenario;

import ro.academyplus.avaj.aircraft.Flyable;

import java.util.List;

public class Scenario {

    private final int simulationsCount;
    private final List<Flyable> aircraftsList;

    public Scenario(int simulationsCount, List<Flyable> aircraftsList) {
        this.simulationsCount = simulationsCount;
        this.aircraftsList = aircraftsList;
    }

    public int getSimulationsCount() {
        return simulationsCount;
    }

    public List<Flyable> getAircraftsList() {
        return aircraftsList;
    }
}