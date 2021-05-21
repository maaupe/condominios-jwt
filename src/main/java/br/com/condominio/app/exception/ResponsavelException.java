package br.com.condominio.app.exception;

import org.springframework.http.HttpStatus;

public class ResponsavelException  extends RuntimeException {

	private final HttpStatus httpStatus;
	
	public ResponsavelException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}
}