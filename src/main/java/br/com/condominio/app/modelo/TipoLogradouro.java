package br.com.condominio.app.modelo;

public enum TipoLogradouro {

	R("Rua"),
	AL("Alameda"),
	AV("avenida"),
	EST("Estrada"),
	PC("Praca"),
	TR("Travessa");
		
	private String descricao;
	
	TipoLogradouro(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	
}
