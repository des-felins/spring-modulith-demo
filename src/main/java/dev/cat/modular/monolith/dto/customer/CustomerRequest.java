package dev.cat.modular.monolith.dto.customer;

import jakarta.validation.constraints.NotNull;

public record CustomerRequest(@NotNull String firstName,
                              @NotNull String lastName,
                              @NotNull long phoneNumber) {
}
