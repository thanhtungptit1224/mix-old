package vn.com.thanhtungptit1224.validation.impl;

import vn.com.thanhtungptit1224.validation.Length;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LengthValidator implements ConstraintValidator<Length, String> {

    private int min;
    private int max;
    private String message;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int length = s.length();

        if (length < min || length > max) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message);
            return false;
        }

        return true;
    }

    @Override
    public void initialize(Length constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.message = constraintAnnotation.message();
    }
}
