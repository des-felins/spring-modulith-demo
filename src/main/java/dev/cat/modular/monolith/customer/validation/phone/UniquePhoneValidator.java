package dev.cat.modular.monolith.customer.validation.phone;

import dev.cat.modular.monolith.customer.repository.CustomerRepository;
import dev.cat.modular.monolith.dto.customer.CustomerRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniquePhoneValidator implements ConstraintValidator<UniquePhoneNumber, CustomerRequest> {

    private final CustomerRepository repository;

    @Override
    public boolean isValid(CustomerRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request == null) return true;
        return repository.findByPhoneNumber(request.phoneNumber()).isEmpty();
    }
}

