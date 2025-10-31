package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.exception.InvalidWeatherException;
import ro.academyplus.avaj.model.Coordinates;
import ro.academyplus.avaj.reporter.Reporter;

public final class Balloon extends Aircraft {
    public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
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
                latitude += 2;
                height += 4;
                Reporter.report(this.getClass().getSimpleName() + "#" + name + "(" + id + "): Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                height -= 5;
                Reporter.report(this.getClass().getSimpleName() + "#" + name + "(" + id + "): Damn you rain! You messed up my balloon.");
                break;
            case "FOG":
                height -= 3;
                Reporter.report(this.getClass().getSimpleName() + "#" + name + "(" + id + "): Can't see anything through this fog.");
                break;
            case "SNOW":
                height -= 15;
                Reporter.report(this.getClass().getSimpleName() + "#" + name + "(" + id + "): It's snowing. We're gonna crash.");
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
        Reporter.report(this.getClass().getSimpleName() + "#" + name + "(" + id + ") landing.");
        weatherTower.unregister(this);
        Reporter.report("Tower says: " + this.getClass().getSimpleName() + "#" + name + "(" + id + ") unregistered from weather tower.");
    }
}
