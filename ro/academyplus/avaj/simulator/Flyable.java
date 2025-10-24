package ro.academyplus.avaj.simulator;

class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    public void registerTower(WeatherTower p_tower) {
        weatherTower = p_tower;
    }
}