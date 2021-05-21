package br.com.condominio.app.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContaDto {
	
	private Long id;
	private String numeroConta;
	private String descricao;

}
