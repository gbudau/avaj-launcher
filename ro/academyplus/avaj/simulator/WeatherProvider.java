package ro.academyplus.avaj.simulator;

final class WeatherProvider {
    private static volatile WeatherProvider instance;
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

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

    public static String getCurrentWeather(Coordinates p_coordinates) {
        WeatherProvider provider = WeatherProvider.getInstance();
        int index = (p_coordinates.getLatitude() + p_coordinates.getLongitude() + p_coordinates.getHeight) % weather.length;
        return weather[index];
    }
}