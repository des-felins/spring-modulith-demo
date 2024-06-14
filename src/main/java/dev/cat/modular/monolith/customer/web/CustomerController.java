package dev.cat.modular.monolith.customer.web;

import dev.cat.modular.monolith.calculator.CalculatorAPI;
import dev.cat.modular.monolith.customer.validation.CorrectShipmentPrice;
import dev.cat.modular.monolith.dto.calculator.CalculatorRequest;
import dev.cat.modular.monolith.dto.customer.CustomerRequest;
import dev.cat.modular.monolith.dto.customer.CustomerResponse;
import dev.cat.modular.monolith.customer.service.CustomerService;
import dev.cat.modular.monolith.globalexceptions.ValidationException;
import dev.cat.modular.monolith.shipment.ShipmentAPI;
import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import dev.cat.modular.monolith.dto.shipment.ShipmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final ShipmentAPI shipmentAPI;
    private final CalculatorAPI calculatorAPI;
    private final CustomerService service;

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ofNullable(service.saveCustomer(request));
    }

    @GetMapping("/customers/{id}/shipments")
    public List<ShipmentResponse> getAllOrdersForCustomer(@PathVariable long id) {
        return shipmentAPI.findOrdersByCustomerId(id);
    }

    @PostMapping("/get-a-quote")
    public ResponseEntity<Double> calculatePrice(@Valid @RequestBody CalculatorRequest request) {
        Double price = calculatorAPI.calculatePrice(request);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @PostMapping("/customers/{id}/new-shipment")
    public ResponseEntity<ShipmentResponse> createShipmentOrder(
            @Valid @RequestBody @CorrectShipmentPrice ShipmentRequest request,
            @PathVariable long id
    ) {
        return ResponseEntity.ofNullable(shipmentAPI.createOrder(request));
    }


}
