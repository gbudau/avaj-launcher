package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.exception.InvalidWeatherException;
import ro.academyplus.avaj.model.Coordinates;
import ro.academyplus.avaj.reporter.Reporter;

public final class JetPlane extends Aircraft {
    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();
        switch (weather) {
            case "SUN":
                latitude += 10;
                height += 2;
                Reporter.report("JetPlane#" + name + "(" + id + "): It's a sunny day, perfect for flying.");
                break;
            case "RAIN":
                latitude += 5;
                Reporter.report("JetPlane#" + name + "(" + id + "): Flying through the rain, stay safe everyone.");
                break;
            case "FOG":
                latitude += 1;
                Reporter.report("JetPlane#" + name + "(" + id + "): Fog ahead, reducing speed.");
                break;
            case "SNOW":
                height -= 7;
                Reporter.report("JetPlane#" + name + "(" + id + "): Snow is making it hard to maintain altitude.");
                break;
            default:
                throw new InvalidWeatherException("Invalid weather " + weather);
        }
        if (height > 0) {
            updateCoordinates(longitude, latitude, height);
        } else {
            unregisterFromWeatherTower();
        }
    }

    private void updateCoordinates(int longitude, int latitude, int height) {
        coordinates = new Coordinates(longitude, latitude, height);
    }

    private void unregisterFromWeatherTower() {
        Reporter.report("JetPlane#" + name + "(" + id + ") landing.");
        weatherTower.unregister(this);
        Reporter.report("Tower says: JetPlane#" + name + "(" + id + ") unregistered from weather tower.");
    }
}
