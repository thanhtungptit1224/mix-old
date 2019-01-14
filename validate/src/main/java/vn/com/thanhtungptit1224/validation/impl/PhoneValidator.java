package vn.com.thanhtungptit1224.validation.impl;

import vn.com.thanhtungptit1224.validation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.matches("\\d{10,11}")) {
            return true;
        } else if (s.matches("\\d{3,4}[-.\\s]\\d{3}[-.\\s]\\d{4}")) {
            return true;
        } else if (s.matches("\\d{3,4}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return true;
        } else {
            return s.matches("\\(\\d{3,4}\\)-\\d{3}-\\d{4}") ? true : s.matches("^\\+(?:[0-9]●?){6,14}[0-9]$");
        }
    }

    @Override
    public void initialize(Phone constraintAnnotation) {

    }


}
