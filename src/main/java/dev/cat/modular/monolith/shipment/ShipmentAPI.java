package dev.cat.modular.monolith.shipment;

import dev.cat.modular.monolith.dto.shipment.ShipmentRequest;
import dev.cat.modular.monolith.dto.shipment.ShipmentResponse;

import java.util.List;

public interface ShipmentAPI {
    ShipmentResponse createOrder(ShipmentRequest request, Long customerId);

    void updateShipmentStatus(Long id, String status);

    List<ShipmentResponse> findOrdersByCustomerId(Long id);

}
