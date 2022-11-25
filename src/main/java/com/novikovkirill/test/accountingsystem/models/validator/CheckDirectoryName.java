package com.novikovkirill.test.accountingsystem.models.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Кастомная аннотация для проверки на уникальность категории.
 */

@Documented
@Constraint(validatedBy = UniqueDirectoryConstraintValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDirectoryName {

    String message() default "Данная категория уже существует";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
