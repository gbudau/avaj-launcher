package ro.academyplus.avaj.simulator;

class Aircraft extends Flyable {
    protected final long id;
    protected final String name;
    protected final Coordinates coordinates;

    protected Aircraft (long p_id, String p_name, Coordinates p_coordinate) {
        id = p_id;
        name = p_name;
        coordinates = p_coordinate;
    }
}