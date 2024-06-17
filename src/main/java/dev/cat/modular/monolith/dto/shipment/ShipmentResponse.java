package dev.cat.modular.monolith.dto.shipment;

public record ShipmentResponse(Long id,
                               Long customerId,
                               double weight,
                               String addressFrom,
                               String addressTo,
                               double price,
                               String deliveryStatus) {
}
