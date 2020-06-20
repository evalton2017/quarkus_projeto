package enums;

public enum TipoEmpresaEnum {
	
	MATRIZ("Matriz"), FILIAL("Filial");

	private String descricao;

	private TipoEmpresaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
