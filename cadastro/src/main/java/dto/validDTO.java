package dto;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidDTOValidator.class})
@Documented
public @interface validDTO {
	
	String message() default "{com.github.evalton2017.quarkus_projeto.cadastro.infra.ValidDTO.message}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};
	

}
