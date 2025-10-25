package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.tower.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower = null;

    public abstract void updateConditions();

    public void registerTower(WeatherTower p_tower) {
        weatherTower = p_tower;
        weatherTower.register(this);
        if (this instanceof Aircraft aircraft) {
            System.out.println("Tower says: " + aircraft.getClass().getName() + "#" + aircraft.name  + "(" + aircraft.id + ") registered to weather tower.");
        }
    }
}