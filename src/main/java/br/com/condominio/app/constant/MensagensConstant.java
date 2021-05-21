package br.com.condominio.app.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensConstant {
	SERVICO_NAO_ENCONTRADO("Servico não encontrado."),
	ERRO_GENERICO("Erro interno identificado. Contate o suporte."),
	ERRO_UNIDADE_NAO_ENCONTRADA("Unidade não encontrada."),
	ERRO_CONDOMINIO_NAO_ENCONTRADo("Condominio não encontrado."),
	ERRO_UNIDADE_CADASTRADA_ANTERIORMENTE("Matéria já possui cadastro."),
	ERRO_CONSOMINIO_CADASTRADO_ANTERIORMENTE("curso já possui cadastro."),
	ERRO_ID_INFORMADO("ID não pode ser informado na operação de cadastro."),
	ERRO_NAO_HA_UNIDADES("Não Ha unidades para serem processadas");
	
	private final String valor;
	
}
