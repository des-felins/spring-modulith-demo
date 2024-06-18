package dev.cat.modular.monolith.customer.validation.address;

import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class UniqueAddressValidator implements ConstraintValidator<UniqueAddress, ShipmentRequest> {

    @Override
    public boolean isValid(ShipmentRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request == null) return true;
        return !Objects.equals(request.addressTo(), request.addressFrom());
    }
}

