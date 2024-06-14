package dev.cat.modular.monolith.customer;


import dev.cat.modular.monolith.dto.customer.CustomerResponse;

public interface CustomerAPI {
    CustomerResponse findCustomerById(Long id);
}
