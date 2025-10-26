package ro.academyplus.avaj.exception;

public class InvalidWeatherException extends RuntimeException {
    public InvalidWeatherException(String errorMessage) {
        super(errorMessage);
    }
}
