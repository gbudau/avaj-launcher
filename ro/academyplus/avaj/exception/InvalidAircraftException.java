package ro.academyplus.avaj.exception;

public class InvalidAircraftException extends RuntimeException {
    public InvalidAircraftException(String errorMessage) {
        super(errorMessage);
    }
}
