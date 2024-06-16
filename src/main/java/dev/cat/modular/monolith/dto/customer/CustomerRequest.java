package dev.cat.modular.monolith.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(@NotNull String firstName,
                              @NotNull String lastName,
                              @NotNull String country,
                              @NotNull String phoneNumber,
                              @NotNull @Email String email) {
}
