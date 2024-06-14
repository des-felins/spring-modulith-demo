package dev.cat.modular.monolith.calculator;

import dev.cat.modular.monolith.dto.calculator.CalculatorRequest;

public interface CalculatorAPI {
    double calculatePrice(CalculatorRequest request);
}
