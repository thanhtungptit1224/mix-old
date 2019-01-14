package vn.com.thanhtungptit1224.validation;

import vn.com.thanhtungptit1224.validation.impl.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailValidator.class})
public @interface Email {

    String message() default "{Invalid.email}";

    /**Required as default and can't change*/
    Class<?>[] groups() default {};

    /**Required as default and can't change*/
    Class<? extends Payload>[] payload() default {};

}
