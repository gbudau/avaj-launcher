package ro.academyplus.avaj.scenario;

import ro.academyplus.avaj.aircraft.Flyable;

import java.util.List;

public record Scenario(int simulationsCount, List<Flyable> aircraftsList) { }