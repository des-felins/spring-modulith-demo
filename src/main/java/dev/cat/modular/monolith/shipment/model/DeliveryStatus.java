package dev.cat.modular.monolith.shipment.model;

public enum DeliveryStatus {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    DELIVERED("DELIVERED");

    DeliveryStatus(String name) {
        this.name = name;
    }

    String name;

    public String getName() {
        return name;
    }
}
