package dev.cat.modular.monolith.customer.globalexceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
