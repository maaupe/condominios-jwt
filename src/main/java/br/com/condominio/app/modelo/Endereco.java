package br.com.condominio.app.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="enderecos")
public class Endereco {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name="bairro", length=40, nullable = false )
    private String bairro;
    
    @Column(name="cidade", length=40, nullable = false )
    private String cidade;
    
    @Column(name="uf", length=2, nullable = false )
    private String uf;
    
    @Column(name="logradouro", length=60, nullable = false )
    private String logradouro;
    
    @Column(name="numero", length=20, nullable = false )
    private String numero;
    
    @Column(name="compelmento", length=20, nullable = false )
    private String complemento;
               
    @Column(name="cep", length=8, nullable = false )
    private String cep;
   
}
