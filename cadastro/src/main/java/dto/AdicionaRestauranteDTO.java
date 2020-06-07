package dto;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.github.evalton2017.quarkus_projeto.cadastro.Restaurante;
import com.sun.istack.NotNull;

@validDTO
public class AdicionaRestauranteDTO implements DTO {
	
	@NotEmpty
	@NotNull
    public String proprietario;
    
	@Pattern(regexp= "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
    public String cnpj;
    
    @Size(min=3,max=30)
    public String nome;
    
    public LocalizacaoDTO localizacao;

    
    public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
    	constraintValidatorContext.disableDefaultConstraintViolation();
    	if(Restaurante.find("cnpj", cnpj).count()>0) {
    		constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado")
    			.addPropertyNode("cnpj")
    			.addConstraintViolation();
    		return false;
    	}
    	return true;
    }

}
