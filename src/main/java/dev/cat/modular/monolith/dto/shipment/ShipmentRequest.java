package dev.cat.modular.monolith.dto.shipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShipmentRequest(@NotNull Long customerId,
                              @NotNull double weight,
                              @NotBlank String addressFrom,
                              @NotBlank String addressTo,
                              @NotNull double price) {
}
