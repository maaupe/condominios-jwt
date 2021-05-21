package br.com.condominio.app.controller.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.com.condominio.app.modelo.Unidade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponsavelDto {

	private Long id;
	private String nome;
	private String cpf_cnpj;
	private int TipoResponsavel;
	
}
