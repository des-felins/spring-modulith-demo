package dev.cat.modular.monolith.dto.calculator;

import dev.cat.modular.monolith.dto.shipment.AddressDto;
import jakarta.validation.constraints.NotNull;

public record CalculatorRequest(@NotNull double weight,
                                @NotNull AddressDto from,
                                @NotNull AddressDto to) {
}
