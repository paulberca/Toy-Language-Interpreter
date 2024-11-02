package model.exception;

public class ExecutionError extends RuntimeException {
    public ExecutionError(String message) {
        super(message);
    }
}
