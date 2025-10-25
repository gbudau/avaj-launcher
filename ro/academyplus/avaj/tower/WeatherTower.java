package ro.academyplus.avaj.tower;

import ro.academyplus.avaj.model.Coordinates;
import ro.academyplus.avaj.provider.WeatherProvider;

public final class WeatherTower extends Tower {
    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        conditionChanged();
    }
}