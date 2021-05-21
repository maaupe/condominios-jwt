package br.com.condominio.app.modelo;

public enum TipoUnidade {

	APTO("APTO"),
	LOFT("LOFT"),
	CASA("CASA");
	
	private String descricao;
	
	TipoUnidade(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
