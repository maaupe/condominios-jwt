package br.com.condominio.app.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class OperacaoException extends RuntimeException {

	private final HttpStatus httpStatus;
	
	public OperacaoException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}
}