package vn.com.thanhtungptit1224.validation;

import vn.com.thanhtungptit1224.validation.impl.LengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {LengthValidator.class})
public @interface Length {

    int min();

    int max();

    String message() default "{Required.length}";

    /**Required as default and can't change*/
    Class<?>[] groups() default {};

    /**Required as default and can't change*/
    Class<? extends Payload>[] payload() default {};

}
