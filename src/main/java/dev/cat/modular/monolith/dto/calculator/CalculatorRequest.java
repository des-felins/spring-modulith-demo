package dev.cat.modular.monolith.dto.calculator;

import jakarta.validation.constraints.NotBlank;

public record CalculatorRequest(@NotBlank double weight,
                                @NotBlank String addressFrom,
                                @NotBlank String addressTo) {
}
