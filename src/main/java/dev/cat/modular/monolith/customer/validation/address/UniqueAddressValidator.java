package dev.cat.modular.monolith.customer.validation.address;

import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueAddressValidator implements ConstraintValidator<UniqueAddress, ShipmentRequest> {

    @Override
    public boolean isValid(ShipmentRequest request, ConstraintValidatorContext constraintValidatorContext) {

        if (request.addressTo() == null && request.addressFrom() == null) return true;
        return !request.addressTo().equals(request.addressFrom());
    }
}

