package com.novikovkirill.test.accountingsystem.models.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Кастомная аннотация для проверки на уникальность электронной почты.
 */

@Documented
@Constraint(validatedBy = UniqueEmailConstraintValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckEmail {

    String message() default "Пользователь с такой электронной почтой уже зарегистрирован";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
