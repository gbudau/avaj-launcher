package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.model.Coordinates;

public final class Helicopter extends Aircraft {
    public Helicopter (long p_id, String p_name, Coordinates p_coordinate) {
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
                longitude += 10;
                height += 2;
                System.out.println("Helicopter#" + name + "(" + id + "): This is hot.");
                break;
            case "RAIN":
                longitude += 5;
                System.out.println("Helicopter#" + name + "(" + id + "): It's raining. Better watch out for lightnings.");
                break;
            case "FOG":
                longitude += 1;
                System.out.println("Helicopter#" + name + "(" + id + "): Can't see anything through this fog.");
                break;
            case "SNOW":
                height -= 12;
                System.out.println("Helicopter#" + name + "(" + id + "): My rotor is going to freeze!");
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
}

    private void updateCoordinates(int longitude, int latitude, int height) {
        coordinates = new Coordinates(longitude, latitude, Math.min(height, 100));
    }

    private void unregisterFromTower() {
        System.out.println("Helicopter#" + name + "(" + id + "): Landing.");
        weatherTower.unregister(this);
        System.out.println("Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower.");
    }
}
