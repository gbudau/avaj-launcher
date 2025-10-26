package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.model.Coordinates;

public final class Jetplane extends Aircraft {
    public Jetplane(long p_id, String p_name, Coordinates p_coordinate) {
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
                System.out.println("Jetplane#" + name + "(" + id + "): It's a sunny day, perfect for flying.");
                break;
            case "RAIN":
                latitude += 5;
                System.out.println("Jetplane#" + name + "(" + id + "): Flying through the rain, stay safe everyone.");
                break;
            case "FOG":
                latitude += 1;
                System.out.println("Jetplane#" + name + "(" + id + "): Fog ahead, reducing speed.");
                break;
            case "SNOW":
                height -= 7;
                System.out.println("Jetplane#" + name + "(" + id + "): Snow is making it hard to maintain altitude.");
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
        System.out.println("Jetplane#" + name + "(" + id + "): Landing.");
        weatherTower.unregister(this);
        System.out.println("Tower says: Jetplane#" + name + "(" + id + ") unregistered from weather tower.");
    }
}
