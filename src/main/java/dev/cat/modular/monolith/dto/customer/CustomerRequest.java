package dev.cat.modular.monolith.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(@NotBlank String firstName,
                              @NotBlank String lastName,
                              @NotBlank String country,
                              @NotBlank String phoneNumber,
                              @NotBlank @Email String email) {
}
