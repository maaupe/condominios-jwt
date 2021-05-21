package br.com.condominio.app.modelo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

	private String mensagem; 
	private int httpStatus;
	private String timeStamp; 
	
}
