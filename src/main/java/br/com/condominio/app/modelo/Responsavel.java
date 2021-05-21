package br.com.condominio.app.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="responsaveis")
public class Responsavel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotEmpty(message = "O nome é obrigatorio. Não pode pode ser vazio")
	@Column(name="nome", length = 50)	
	private String nome;
	
	@NotEmpty(message = "O cpf/cnpj é obrigatorio. Não pode pode ser vazio")
	@Column(name="cpf_cnpj", length = 14)	
	private String cpf_cnpj;
	
	@Column(name="tipo_reponsavel") //1=fisica / juridica
	private	int tipoResponsavel;
	
    //@OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY)
    //private List<Unidade> unidades;
	
}
