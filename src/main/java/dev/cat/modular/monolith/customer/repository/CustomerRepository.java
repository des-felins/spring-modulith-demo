package dev.cat.modular.monolith.customer.repository;

import dev.cat.modular.monolith.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByPhoneNumber(long phoneNumber);
}
