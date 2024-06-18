package dev.cat.modular.monolith.admin.web;

import dev.cat.modular.monolith.customer.CustomerAPI;
import dev.cat.modular.monolith.dto.customer.CustomerResponse;
import dev.cat.modular.monolith.shipment.ShipmentAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Validated
public class AdminController {

    private final ShipmentAPI shipmentAPI;
    private final CustomerAPI customerAPI;

    @Autowired
    public AdminController(ShipmentAPI shipmentAPI, CustomerAPI customerAPI) {
        this.shipmentAPI = shipmentAPI;
        this.customerAPI = customerAPI;
    }


    @PostMapping("/shipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateShipmentStatus(@PathVariable long id, @RequestBody String status) {
        shipmentAPI.updateShipmentStatus(id, status);
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable long id) {
        return ResponseEntity.ofNullable(customerAPI.findCustomerById(id));
    }
}
