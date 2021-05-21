package br.com.condominio.app.message.response;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Response<T> extends RepresentationModel<Response<T>> {

	 private int statusCode;
	 private T data;
	 private String timeStamp;
	 
	 public Response() {
		 SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd-HH:mmss");
		 Date data = new Date();
		 this.timeStamp = formatador.format(data);
	 }
}
