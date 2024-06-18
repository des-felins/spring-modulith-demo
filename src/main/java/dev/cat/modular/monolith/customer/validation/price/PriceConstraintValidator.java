package dev.cat.modular.monolith.customer.validation.price;

import dev.cat.modular.monolith.calculator.CalculatorAPI;
import dev.cat.modular.monolith.dto.calculator.CalculatorRequest;
import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceConstraintValidator implements ConstraintValidator<CorrectShipmentPrice, ShipmentRequest> {

    private final CalculatorAPI calculatorAPI;

    @Override
    public boolean isValid(ShipmentRequest request, ConstraintValidatorContext constraintValidatorContext) {
        double price;
        if (request == null) return true;

        price = calculatorAPI.calculatePrice(
                new CalculatorRequest(request.weight(), request.addressFrom(), request.addressTo()));
        return request.price() == price;
    }
}
