package ro.academyplus.avaj.factory;

import ro.academyplus.avaj.aircraft.Flyable;
import ro.academyplus.avaj.aircraft.Balloon;
import ro.academyplus.avaj.aircraft.Helicopter;
import ro.academyplus.avaj.aircraft.JetPlane;
import ro.academyplus.avaj.exception.InvalidAircraftException;
import ro.academyplus.avaj.model.Coordinates;

import java.util.concurrent.atomic.AtomicLong;

public final class AircraftFactory {
    private static volatile AircraftFactory instance = null;
    private static final AtomicLong id = new AtomicLong(0);

    private AircraftFactory() {}

    private static AircraftFactory getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (AircraftFactory.class) {
            if (instance == null) {
                instance = new AircraftFactory();
            }
            return instance;
        }
    }

    private static long getNextId() {
        return id.incrementAndGet();
    }

    private static Flyable createAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        return switch (p_type) {
            case "Helicopter" -> new Helicopter(getNextId(), p_name, p_coordinates);
            case "JetPlane" -> new JetPlane(getNextId(), p_name, p_coordinates);
            case "Balloon" -> new Balloon(getNextId(), p_name, p_coordinates);
            default -> throw new InvalidAircraftException("Invalid aircraft: " + p_type);
        };
    }

    public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        return AircraftFactory
                .getInstance()
                .createAircraft(p_type, p_name, p_coordinates);
    }
}
