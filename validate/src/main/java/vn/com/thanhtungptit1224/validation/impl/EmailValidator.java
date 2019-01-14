package vn.com.thanhtungptit1224.validation.impl;

import vn.com.thanhtungptit1224.validation.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", 2);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    @Override
    public void initialize(Email constraintAnnotation) {

    }
}
