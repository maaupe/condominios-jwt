package br.com.condominio.app.modelo.filtro;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BoletoFilter {
	
	private String vencimento;
	private Long idCondominio;
	private Long idUnidade;
	
}
