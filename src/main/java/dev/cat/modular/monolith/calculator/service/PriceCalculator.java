package dev.cat.modular.monolith.calculator.service;

import dev.cat.modular.monolith.calculator.CalculatorAPI;
import dev.cat.modular.monolith.dto.calculator.CalculatorRequest;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculator implements CalculatorAPI {
    @Override
    public double calculatePrice(CalculatorRequest request) {

        //that's pure placeholder for the simplicity sake :)
        return 10.0 * request.weight();

    }
}
