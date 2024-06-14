package dev.cat.modular.monolith.customer.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceConstraintValidator implements ConstraintValidator<ShipmentPrice, Double> {

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
