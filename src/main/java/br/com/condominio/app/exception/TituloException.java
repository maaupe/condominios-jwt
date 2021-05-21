package br.com.condominio.app.exception;

import org.springframework.http.HttpStatus;

public class TituloException  extends RuntimeException {

	private final HttpStatus httpStatus;
	
	public TituloException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}
}