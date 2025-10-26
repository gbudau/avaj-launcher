package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.exception.InvalidWeatherException;
import ro.academyplus.avaj.model.Coordinates;

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
                System.out.println("Balloon#" + name + "(" + id + "): Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                height -= 5;
                System.out.println("Balloon#" + name + "(" + id + "): Damn you rain! You messed up my balloon.");
                break;
            case "FOG":
                height -= 3;
                System.out.println("Balloon#" + name + "(" + id + "): Can't see anything through this fog.");
                break;
            case "SNOW":
                height -= 15;
                System.out.println("Balloon#" + name + "(" + id + "): It's snowing. We're gonna crash.");
                break;
            default:
                throw new InvalidWeatherException("Invalid weather " + weather);
        }

        if (height > 0) {
            updateCoordinates(longitude, latitude, height);
        } else {
            unregisterFromTower();
        }
    }

    private void updateCoordinates(int longitude, int latitude, int height) {
        coordinates = new Coordinates(longitude, latitude, Math.min(height, 100));
    }

    private void unregisterFromTower() {
        System.out.println("Balloon#" + name + "(" + id + "): Landing.");
        weatherTower.unregister(this);
        System.out.println("Tower says: Balloon#" + name + "(" + id + ") unregistered from weather tower.");
    }
}
