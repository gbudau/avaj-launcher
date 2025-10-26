package ro.academyplus.avaj.exception;

public class InvalidWeatherException extends Exception {
    public InvalidWeatherException(String errorMessage) {
        super(errorMessage);
    }
}
