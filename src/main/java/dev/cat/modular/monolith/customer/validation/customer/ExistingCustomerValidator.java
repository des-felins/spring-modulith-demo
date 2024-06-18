package dev.cat.modular.monolith.customer.validation.customer;

import dev.cat.modular.monolith.customer.repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistingCustomerValidator implements ConstraintValidator<ExistingCustomer, Long> {

    private CustomerRepository repository;

    @Autowired
    public void setRepository(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        if (id == null) return true;
        return repository.existsById(id);
    }
}
