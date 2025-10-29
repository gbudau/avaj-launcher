package ro.academyplus.avaj.provider;

import ro.academyplus.avaj.model.Coordinates;

import java.lang.Math;
import java.util.Objects;

public final class WeatherProvider {
    private static volatile WeatherProvider instance;
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() { }

    private static WeatherProvider getInstance() {
        WeatherProvider weatherProvider = instance;

        if (weatherProvider != null) {
            return weatherProvider;
        }
        synchronized (WeatherProvider.class) {
            if (instance == null) {
                instance = new WeatherProvider();
            }
            return instance;
        }
    }

    private static String getWeather(Coordinates p_coordinates) {
        int hash = Objects.hash(
                p_coordinates.getLatitude(),
                p_coordinates.getLongitude(),
                p_coordinates.getHeight()
        );
        int index = Math.floorMod(hash, weather.length);
        return weather[index];
    }

    public static String getCurrentWeather(Coordinates p_coordinates) {
        return WeatherProvider.getInstance().getWeather(p_coordinates);
    }
}