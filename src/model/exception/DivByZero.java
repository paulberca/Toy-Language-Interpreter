package model.exception;

public class DivByZero extends RuntimeException {
    public DivByZero(String message) {
        super(message);
    }
}
