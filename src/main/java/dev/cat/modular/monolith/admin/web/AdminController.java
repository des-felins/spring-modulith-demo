package dev.cat.modular.monolith.admin.web;

import dev.cat.modular.monolith.shipment.ShipmentAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ShipmentAPI shipmentAPI;

    @Autowired
    public AdminController(ShipmentAPI shipmentAPI) {
        this.shipmentAPI = shipmentAPI;
    }


    @PostMapping("/shipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateShipmentStatus(@PathVariable long id, @RequestBody String status) {
        shipmentAPI.updateShipmentStatus(id, status);
    }
}
