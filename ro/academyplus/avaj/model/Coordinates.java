package ro.academyplus.avaj.model;

import ro.academyplus.avaj.exception.InvalidCoordinateException;

public final class Coordinates {
    private final int longitude;
    private final int latitude;
    private final int height;

    public Coordinates(int longitude, int latitude, int height) {
        if (longitude <= 0) {
            throw new InvalidCoordinateException("Longitude must be positive.");
        }
        if (latitude <= 0) {
            throw new InvalidCoordinateException("Latitude must be positive.");
        }
        if (height < 0) {
            throw new InvalidCoordinateException("Height cannot be negative.");
        }
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = Math.min(height, 100);
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }
}