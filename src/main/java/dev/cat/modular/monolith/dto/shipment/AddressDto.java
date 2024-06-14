package dev.cat.modular.monolith.dto.shipment;

public record AddressDto(Long id,
                         String city,
                         String street,
                         String house,
                         String apartment,
                         String zipCode) {

}
