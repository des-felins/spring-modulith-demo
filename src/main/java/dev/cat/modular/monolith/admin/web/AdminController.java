package dev.cat.modular.monolith.admin.web;

import dev.cat.modular.monolith.customer.CustomerAPI;
import dev.cat.modular.monolith.dto.customer.CustomerResponse;
import dev.cat.modular.monolith.shipment.ShipmentAPI;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final ShipmentAPI shipmentAPI;
    private final CustomerAPI customerAPI;

    @PostMapping("/shipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateShipmentStatus(
            @NotNull
            @PathVariable
            long id,
            @NotNull
            @RequestBody
            String status) {
        shipmentAPI.updateShipmentStatus(id, status);
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> getCustomerById(
            @NotNull
            @PathVariable
            long id) {
        return ResponseEntity.ofNullable(customerAPI.findCustomerById(id));
    }
}
