package api.exception.handlers;

public class HandlerError extends RuntimeException {
    public HandlerError(String msg) {
        super(msg);
    }
}
