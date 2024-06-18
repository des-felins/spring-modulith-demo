package dev.cat.modular.monolith.customer.validation.address;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueAddressValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueAddress {

    String message() default "Shipment must be delivered to a different address.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
