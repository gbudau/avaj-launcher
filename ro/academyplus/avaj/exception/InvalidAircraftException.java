package ro.academyplus.avaj.exception;

public class InvalidAircraftException extends RuntimeException {
    public InvalidAircraftException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidAircraftException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
