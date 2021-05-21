package br.com.condominio.app.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.condominio.app.exception.CondominioException;
import br.com.condominio.app.message.response.Response;
import br.com.condominio.app.modelo.ErrorResponse;
import br.com.condominio.app.modelo.ErrorResponse.ErrorResponseBuilder;

@ControllerAdvice
public class ResourceHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<Map<String,String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m){
		
		Map<String,String> erros = new HashMap<>();
		m.getBindingResult().getAllErrors().forEach(erro->{
			String campo = ((FieldError)erro).getField();
			String mensagem = erro.getDefaultMessage();
			erros.put(campo,mensagem);
		});
		
		Response<Map<String,String>> response = new Response<>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setData(erros);
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
	
	@ExceptionHandler(CondominioException.class)
	public ResponseEntity<Response<String>> handlerCondominioException(CondominioException m) {
		
		
		
		Response<String> response = new Response<>();
		response.setStatusCode(m.getHttpStatus().value());
		response.setData(m.getMessage());
		return ResponseEntity.status(m.getHttpStatus()).body(response);
		
		/*SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd-HH:mmss");
		Date data = new Date();
		
		ErrorResponseBuilder erro = ErrorResponse.builder();
		erro.httpStatus(m.getHttpStatus().value());
		erro.mensagem(m.getMessage());
		//System.currentTimeMillis()
		erro.timeStamp(formatador.format(data));
		return ResponseEntity.status(m.getHttpStatus()).body(erro.build());*/
	}
	
	
}
