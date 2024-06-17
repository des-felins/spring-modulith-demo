package dev.cat.modular.monolith.customer.globalexceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
