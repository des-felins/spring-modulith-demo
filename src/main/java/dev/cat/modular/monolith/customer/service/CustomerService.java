package dev.cat.modular.monolith.customer.service;

import dev.cat.modular.monolith.ShipmentCreateEvent;
import dev.cat.modular.monolith.ShipmentStatusChangeEvent;
import dev.cat.modular.monolith.customer.CustomerAPI;
import dev.cat.modular.monolith.customer.mapper.CustomerMapper;
import dev.cat.modular.monolith.customer.model.Customer;
import dev.cat.modular.monolith.customer.repository.CustomerRepository;
import dev.cat.modular.monolith.dto.customer.CustomerRequest;
import dev.cat.modular.monolith.dto.customer.CustomerResponse;
import dev.cat.modular.monolith.globalexceptions.NotFoundException;
import dev.cat.modular.monolith.globalexceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements CustomerAPI {

    private final CustomerRepository repository;

    public CustomerResponse saveCustomer(CustomerRequest request) {
        Customer customer = CustomerMapper.INSTANCE.mapToCustomer(request);

        if (repository.findByPhoneNumber(customer.getPhoneNumber()).isPresent()) {
            throw new ValidationException("customer with phone number "
                    + customer.getPhoneNumber() + " already exists!");
        }
        return CustomerMapper.INSTANCE.mapToCustomerResponse(repository.save(customer));
    }

    @Override
    public CustomerResponse findCustomerById(Long id) {
        Optional<Customer> customerOpt = repository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new NotFoundException("Couldn't find customer with id: " + id);
        }
        return CustomerMapper.INSTANCE.mapToCustomerResponse(customerOpt.get());
    }

    @ApplicationModuleListener
    void onUpdateShipmentStatusEvent(ShipmentStatusChangeEvent event) {
        log.info("Changed status of shipment {}", event.orderId());
    }

    @ApplicationModuleListener
    void onShipmentCreateEvent(ShipmentCreateEvent event) {
        log.info("Created shipment {}", event.orderId());
    }


}
