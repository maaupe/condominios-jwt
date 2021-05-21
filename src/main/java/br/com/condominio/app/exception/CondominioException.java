package br.com.condominio.app.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CondominioException extends RuntimeException {

	private final HttpStatus httpStatus;
	
	public CondominioException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}
}
