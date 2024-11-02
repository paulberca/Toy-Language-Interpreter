package model.exception;

public class UndefinedVariable extends RuntimeException {
    public UndefinedVariable(String message) {
        super(message);
    }
}
