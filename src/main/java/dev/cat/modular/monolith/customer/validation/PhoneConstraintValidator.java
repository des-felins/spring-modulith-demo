package dev.cat.modular.monolith.customer.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import dev.cat.modular.monolith.dto.customer.CustomerRequest;
import dev.cat.modular.monolith.customer.globalexceptions.ValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<CorrectPhoneNumber, CustomerRequest> {

    @Override
    public boolean isValid(CustomerRequest request, ConstraintValidatorContext constraintValidatorContext) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phone;
        boolean result;

        if (request.phoneNumber() == null) {
            result = true;
        } else {
            try {
                phone = phoneNumberUtil.parse(request.phoneNumber(), Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
                result = phoneNumberUtil.isValidNumber(phone);
            } catch (NumberParseException e) {
                throw new ValidationException("Must be a valid mobile number starting with +");
            }
        }

        return result;
    }

}
