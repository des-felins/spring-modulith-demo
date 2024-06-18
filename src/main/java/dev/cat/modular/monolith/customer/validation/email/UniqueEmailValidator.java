package dev.cat.modular.monolith.customer.validation.email;

import dev.cat.modular.monolith.customer.repository.CustomerRepository;
import dev.cat.modular.monolith.dto.customer.CustomerRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, CustomerRequest> {

    private final CustomerRepository repository;

    @Override
    public boolean isValid(CustomerRequest request, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;

        if (request.phoneNumber() == null) {
            result = true;
        } else {
            result = repository.findByEmail(request.email()).isEmpty();
        }
        return result;
    }
}

