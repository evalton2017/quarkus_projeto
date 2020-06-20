package br.quarkus_projeto.cadastro;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.quarkus_projeto.cadastro.localizacao.Localizacao;
import br.quarkus_projeto.cadastro.prato.Prato;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="restaurante")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Restaurante extends PanacheEntityBase {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String proprietario;
    
    private String cnpj;
    
    private String nome;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Localizacao localizacao;
    
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Prato> pratos;
    
    @CreationTimestamp
    private Date dataCriacao;
    
    @UpdateTimestamp
    private Date dataAtualizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Set<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(Set<Prato> pratos) {
		this.pratos = pratos;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
    

    

}
