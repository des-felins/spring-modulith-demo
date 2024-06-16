package dev.cat.modular.monolith.customer.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectPhoneNumber {

    String message() default "Invalid phone number.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
