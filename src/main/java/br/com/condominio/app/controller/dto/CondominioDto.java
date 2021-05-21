package br.com.condominio.app.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.condominio.app.modelo.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CondominioDto {
	
	private Long id;

	@NotBlank(message = "nao pode ser vazio")
	@Size(min = 10, max = 50, message = "o tamanho tem que estar entre 10 e 50")
	@Column(nullable = false)
	private String sindico;

	@Column(name = "razaosocial", nullable = false)
	private String razaoSocial;

	@Column(nullable = false)
	private String cnpj;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "datacadastro")
	private LocalDate dataCadastro;

	@Column(name="dia_vencimento")
	private int diaVencimento;
	
	
	@Column(nullable = false)
	private String status;

	@Digits(integer=12, fraction=2)
	@Column(name="valor_condominio")
	private BigDecimal valorCondominio;
	
	@Digits(integer=12, fraction=2)
	@Column(name="valor_cota_extra")
	private BigDecimal valorCotaExtra;   
	
	@Column(name="valor_garagem_extra", precision=12, scale=2)
	private BigDecimal valorGaragemExtra;   
	
	private Endereco endereco;
	
}
