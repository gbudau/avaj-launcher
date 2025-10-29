package ro.academyplus.avaj.exception;

public class InvalidCoordinateException extends RuntimeException {
    public InvalidCoordinateException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidCoordinateException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
