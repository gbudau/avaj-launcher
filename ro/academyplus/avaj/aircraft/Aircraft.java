package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.model.Coordinates;

public class Aircraft extends Flyable {
    protected final long id;
    protected final String name;
    protected Coordinates coordinates;

    protected Aircraft (long p_id, String p_name, Coordinates p_coordinate) {
        id = p_id;
        name = p_name;
        coordinates = p_coordinate;
    }

    @Override
    public void updateConditions() {
        // no-op - to be implemented in subclasses
    }
}