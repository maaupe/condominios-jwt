package br.com.condominio.app.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="condominios")
public class Condominio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "nao pode ser vazio")
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

	@Digits(integer=12, fraction=2)
	@Column(name="valor_garagem_extra")
	private BigDecimal valorGaragemExtra;   
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;
	   
    @PrePersist
    public void PrePersist() {
    	dataCadastro = LocalDate.now();
    }
    
	

}
