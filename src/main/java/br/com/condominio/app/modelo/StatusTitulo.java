package br.com.condominio.app.modelo;

public enum StatusTitulo {

	ABERTO("Aberto"),
	GERADO("Gerado"),
	RECEBIDO("Recebido");
	
	private String descricao;
	
	private StatusTitulo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}

