package dev.cat.modular.monolith.dto.shipment;

public record ShipmentResponse(Long id,
                               Long customerId,
                               double weight,
                               AddressDto from,
                               AddressDto to,
                               double price,
                               String deliveryStatus) {
}
