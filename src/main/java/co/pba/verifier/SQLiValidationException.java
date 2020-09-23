package co.pba.verifier;

public class SQLiValidationException extends Exception {
    private static final long serialVersionUID = -7546743399303478923L;

    public SQLiValidationException(String message) {
        super(message);
    }
}
