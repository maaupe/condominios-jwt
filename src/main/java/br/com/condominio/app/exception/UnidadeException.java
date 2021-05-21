package br.com.condominio.app.exception;

import org.springframework.http.HttpStatus;

public class UnidadeException extends RuntimeException {

	private final HttpStatus httpStatus;
	
	public UnidadeException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}	

}
