package dev.cat.modular.monolith.customer.validation.address;

import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueAddressValidator implements ConstraintValidator<UniqueAddress, ShipmentRequest> {

    @Override
    public boolean isValid(ShipmentRequest request, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;

        if (request.addressTo() == null && request.addressFrom() == null) {
            result = true;
        } else {
            result = !request.addressTo().equals(request.addressFrom());
        }
        return result;
    }
}

