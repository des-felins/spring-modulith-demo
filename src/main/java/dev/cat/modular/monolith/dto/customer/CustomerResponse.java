package dev.cat.modular.monolith.dto.customer;

public record CustomerResponse(Long id,
                               String firstName,
                               String lastName,
                               long phoneNumber) {
}
