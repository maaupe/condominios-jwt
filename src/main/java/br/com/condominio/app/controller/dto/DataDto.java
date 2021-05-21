package br.com.condominio.app.controller.dto;

public class DataDto {

	private UsuarioDto data;
	private String Token ;
	
	
	public DataDto() {}




	public UsuarioDto getData() {
		return data;
	}




	public void setData(UsuarioDto data) {
		this.data = data;
	}




	public String getToken() {
		return Token;
	}




	public void setToken(String token) {
		Token = token;
	}
	
	
	
}
