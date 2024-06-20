package dev.cat.modular.monolith.customer.web;

import dev.cat.modular.monolith.calculator.CalculatorAPI;
import dev.cat.modular.monolith.customer.service.CustomerService;
import dev.cat.modular.monolith.customer.validation.address.UniqueAddress;
import dev.cat.modular.monolith.customer.validation.customer.ExistingCustomer;
import dev.cat.modular.monolith.customer.validation.email.UniqueEmail;
import dev.cat.modular.monolith.customer.validation.phone.CorrectPhoneNumber;
import dev.cat.modular.monolith.customer.validation.phone.UniquePhoneNumber;
import dev.cat.modular.monolith.customer.validation.price.CorrectShipmentPrice;
import dev.cat.modular.monolith.dto.calculator.CalculatorRequest;
import dev.cat.modular.monolith.dto.customer.CustomerRequest;
import dev.cat.modular.monolith.dto.customer.CustomerResponse;
import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import dev.cat.modular.monolith.dto.shipment.ShipmentResponse;
import dev.cat.modular.monolith.shipment.ShipmentAPI;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final ShipmentAPI shipmentAPI;
    private final CalculatorAPI calculatorAPI;
    private final CustomerService service;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResponse> createCustomer(
            @NotNull
            @Valid
            @RequestBody
            @CorrectPhoneNumber
            @UniquePhoneNumber
            @UniqueEmail
            CustomerRequest request) {
        return ResponseEntity.ofNullable(service.saveCustomer(request));
    }

    @GetMapping("/customers/{id}/shipments")
    @ResponseStatus(HttpStatus.OK)
    public List<ShipmentResponse> getAllOrdersForCustomer(
            @NotNull
            @PathVariable
            @ExistingCustomer
            long id) {
        return shipmentAPI.findOrdersByCustomerId(id);
    }

    @PostMapping("/quote")
    public ResponseEntity<Double> calculatePrice(
            @NotNull
            @Valid
            @RequestBody
            CalculatorRequest request) {
        Double price = calculatorAPI.calculatePrice(request);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @PostMapping("/customers/{id}/shipment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ShipmentResponse> createShipmentOrder(
            @NotNull
            @Valid
            @RequestBody
            @CorrectShipmentPrice
            @UniqueAddress
            ShipmentRequest request,
            @NotNull
            @PathVariable
            @ExistingCustomer
            long id
    ) {
        return ResponseEntity.ofNullable(shipmentAPI.createOrder(request, id));
    }


}
