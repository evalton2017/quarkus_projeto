package enums;

public enum TipoTelefoneEnum {
	
	CELULAR("Celular"), COMERCIAL("Comercial");

	private String descricao;

	private TipoTelefoneEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
