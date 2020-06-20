package br.quarkus_projeto.cadastro;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.quarkus_projeto.cadastro.localizacao.LocalizacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UpdateRestauranteDTO {
	
	private Long id;
	        
    private String dataCriacao;
    
    @NotEmpty
	@NotNull
    private String proprietario;
    
	@Pattern(regexp= "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
    private String cnpj;
    
    @Size(min=3,max=30)
    private String nome;
    
    private LocalizacaoDTO localizacao;

    
    @SuppressWarnings("unused")
	private boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
    	constraintValidatorContext.disableDefaultConstraintViolation();
    	if(Restaurante.find("cnpj", cnpj).count()>0) {
    		constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado")
    			.addPropertyNode("cnpj")
    			.addConstraintViolation();
    		return false;
    	}
    	return true;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public String getProprietario() {
		return proprietario;
	}


	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalizacaoDTO getLocalizacao() {
		return localizacao;
	}


	public void setLocalizacao(LocalizacaoDTO localizacao) {
		this.localizacao = localizacao;
	}
    
    
    

}
