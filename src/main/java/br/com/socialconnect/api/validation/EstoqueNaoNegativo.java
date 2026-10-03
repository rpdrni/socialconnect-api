package br.com.socialconnect.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EstoqueNaoNegativoValidator.class)

public @interface EstoqueNaoNegativo {
    String message() default "O estoque não pode ser negativo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
