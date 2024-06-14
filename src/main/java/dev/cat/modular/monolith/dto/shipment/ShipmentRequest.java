package dev.cat.modular.monolith.dto.shipment;

import jakarta.validation.constraints.NotNull;

public record ShipmentRequest(@NotNull Long customerId,
                              @NotNull double weight,
                              @NotNull AddressDto from,
                              @NotNull AddressDto to,
                              double price) {
}
