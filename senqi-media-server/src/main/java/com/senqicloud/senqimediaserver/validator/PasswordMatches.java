package com.senqicloud.senqimediaserver.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {

    String message() default "两次输入的密码不一致";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
