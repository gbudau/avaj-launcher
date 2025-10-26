package ro.academyplus.avaj.exception;

public class InvalidWeatherException extends RuntimeException {
    public InvalidWeatherException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidWeatherException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
