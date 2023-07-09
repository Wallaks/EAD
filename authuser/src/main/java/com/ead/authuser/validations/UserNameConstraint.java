package com.ead.authuser.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UserNameConstraintImpl.class) // Qual é classe que vai conter a validação específica
@Target({ ElementType.METHOD, ElementType.FIELD }) // Onde podemos ultilizar esssa anotação
@Retention(RetentionPolicy.RUNTIME) // Definir quando essa validação vai ocorrer (temop de execução)
public @interface UserNameConstraint {

	String message() default "Invalid username"; // Mensagem quando ocorrer erro

	Class<?>[] groups() default {}; // Grupo de validação

	Class<? extends Payload>[] payload() default {}; // Nível que vai ocorrer tal erro
	// São parametros default do BeanValidation
}