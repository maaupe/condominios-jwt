package br.com.condominio.app.controller.dto;

import br.com.condominio.app.modelo.Conta;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OperacaoDto {
	
	 private Long id;
	 private String descricao;
	 private Conta conta;
	 
}
