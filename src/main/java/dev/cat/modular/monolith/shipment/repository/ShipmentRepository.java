package dev.cat.modular.monolith.shipment.repository;

import dev.cat.modular.monolith.shipment.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByCustomerId(Long customerId);

}
