package vn.com.thanhtungptit1224.validation;

import vn.com.thanhtungptit1224.validation.impl.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PhoneValidator.class})
public @interface Phone {

    String message() default "{Invalid.phone}";

    /**Required as default and can't change*/
    Class<?>[] groups() default {};

    /**Required as default and can't change*/
    Class<? extends Payload>[] payload() default {};

}
