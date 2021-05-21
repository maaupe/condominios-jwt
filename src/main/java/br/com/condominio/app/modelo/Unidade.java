package br.com.condominio.app.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name="unidades")
public class Unidade {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
		@Column(name="numero", length=20)
		private String numero;
		
		@Enumerated(value = EnumType.STRING)
		private TipoUnidade tipounidade;
		
		@Column(name="garagem")
	    private boolean garagem;
	    
	    @ManyToOne
	    @JoinColumn(name="condominio_id")
        private Condominio condominio;	
	    
		@ManyToOne
		@JoinColumn(name = "responsavel_id")
	    private Responsavel responsavel;
	    
}
