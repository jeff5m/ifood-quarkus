package com.github.jeff5m.ifood.cadastro.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueFieldValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Unique {
    String message() default "Unique.message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
