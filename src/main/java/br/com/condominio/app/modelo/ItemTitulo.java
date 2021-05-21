package br.com.condominio.app.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
@Table(name="item_titulo")
public class ItemTitulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotEmpty(message = "nao pode ser vazio")
	@Column(name="referencia", length = 7)	
	private String referencia;

	@Column(precision=12, scale=2)
	private BigDecimal valor;    
	
	@ManyToOne
	@JoinColumn(name = "operacao_id")
    private Operacao operacao;
	
	//@ManyToOne
    //@JoinColumn(name = "titulo_id")
    //private Titulo titulo; 
	
}
