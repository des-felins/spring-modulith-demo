package dev.cat.modular.monolith.shipment.repository;

import dev.cat.modular.monolith.shipment.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
