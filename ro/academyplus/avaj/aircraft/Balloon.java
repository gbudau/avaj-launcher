package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.model.Coordinates;

public final class Balloon extends Aircraft {
    Balloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {

    }
}
