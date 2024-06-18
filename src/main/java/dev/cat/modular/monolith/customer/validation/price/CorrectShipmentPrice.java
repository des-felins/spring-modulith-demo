package dev.cat.modular.monolith.customer.validation.price;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PriceConstraintValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectShipmentPrice {
    String message() default "Shipment price doesn't match the requested price.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
