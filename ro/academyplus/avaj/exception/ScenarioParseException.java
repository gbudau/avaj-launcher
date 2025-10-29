package ro.academyplus.avaj.exception;

public class ScenarioParseException extends RuntimeException {

    public ScenarioParseException(String errorMessage) {
        super(errorMessage);
    }

    public ScenarioParseException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
