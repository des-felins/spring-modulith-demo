package dev.cat.modular.monolith.customer.validation.customer;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistingCustomerValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingCustomer {

    String message() default "Customer with this id doesn't exist.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
