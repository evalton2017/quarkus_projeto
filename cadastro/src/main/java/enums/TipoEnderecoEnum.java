package enums;

public enum TipoEnderecoEnum {
	
	RESIDENCIAL("Residencial"), COMERCIAL("Comercial"), OUTROS("Outros") ;

	private String descricao;

	private TipoEnderecoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
