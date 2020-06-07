package dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDTOValidator implements ConstraintValidator<validDTO, DTO> {
	
	public void initalize(validDTO constraitAnnotation) {
		
	}
	

	@Override
	public boolean isValid(DTO dto, ConstraintValidatorContext constraitValidatorContext) {
		return dto.isValid(constraitValidatorContext);
	}

}
