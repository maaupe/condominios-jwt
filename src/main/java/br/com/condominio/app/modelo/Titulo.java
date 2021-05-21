package br.com.condominio.app.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.format.annotation.NumberFormat;

import br.com.condominio.app.util.Utilidade;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@ToString
@Entity
@Table(name="titulos")
public class Titulo extends Auditoria<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Setter(AccessLevel.NONE)
	@Column(name="nossonumero", length=11)
    private String nossoNumero;
	
	@Column(name = "dt_vencimento", columnDefinition = "DATE")	
    private LocalDate vencimento;
    
	@Column(name = "dt_criacao", columnDefinition = "DATE")
	private LocalDate criacao;
	
	@NumberFormat(pattern = "#,##0.00")
    @Column(precision=12, scale=2)
    private BigDecimal valor;
	
	@NumberFormat(pattern = "#,##0.00")
    @Column(precision=12, scale=2)
    private BigDecimal despesa;
	
	@NumberFormat(pattern = "#,##0.00")
    @Column(precision=12, scale=2)
    private BigDecimal outrasdespesa;
	
	@NumberFormat(pattern = "#,##0.00")
    @Column(precision=12, scale=2)
    private BigDecimal juros;
	
	@NumberFormat(pattern = "#,##0.00")
    @Column(precision=12, scale=2)
    private BigDecimal iof;
    
	@NumberFormat(pattern = "#,##0.00")
	@Column(precision=12, scale=2)
    private BigDecimal abatimento;
	
	@NumberFormat(pattern = "#,##0.00")
    @Column(precision=12, scale=2)
    private BigDecimal desconto;
	
	@NumberFormat(pattern = "#,##0.00")
    @Column(precision=12, scale=2)
    private BigDecimal valorpago;
    
    @Column(name = "dt_credito", columnDefinition = "DATE")   
    private LocalDate dtcredito;
    
    @Enumerated(EnumType.STRING)
    private StatusTitulo status;
        
    
    @ToString.Exclude
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="titulo_id")
    private List<ItemTitulo> itens;
    
    @ManyToOne(optional=false)
    private Unidade unidade;
    
    @PrePersist
    public void PrePersist() {
    	criacao = LocalDate.now();

    	String ano = String.valueOf(getVencimento().getYear()).substring(2, 4);	
    	String mes = "";
    	String dia = "";
    	 
    	if(getVencimento().getMonthValue() < 10) {
    		 mes = String.format("%02d", getVencimento().getMonthValue())  ;
    	}else {
    		 mes = String.valueOf(getVencimento().getMonthValue());
    	}
    	if(getVencimento().getDayOfMonth() < 10) {
    		 dia = String.format("%02d", getVencimento().getDayOfMonth()) ;	
    	}else {
    		 dia = String.valueOf(getVencimento().getDayOfMonth()) ;
    	}
    	
    	String numeroUnidade = Utilidade.padLeftZeros(unidade.getNumero(), 5);
    	
    	setNossonumero(ano+mes+dia+numeroUnidade);
    }
    
           
    public Titulo() {
    	itens = new ArrayList<ItemTitulo>();
    }
        
	public void adicionaItemTitulo(ItemTitulo item) {
		  
		if(item==null) {
			return;
		}
		/*if(getItens().contains(item)){
			this.itens.set(getItens().indexOf(item),item);
		}else {*/
			this.itens.add(item);
		//}
		
	}
	
	
	public BigDecimal getTotalTitulo(){
		
		BigDecimal totalTitulo = BigDecimal.ZERO;

        for (ItemTitulo itemTitulo : itens) {
        	 			
 			if(itemTitulo.getOperacao().getId() < 200){
 				
 				totalTitulo = totalTitulo.add(itemTitulo.getValor());
 				
 			}else{
 				
 				totalTitulo=totalTitulo.subtract(itemTitulo.getValor());
 			}
		}
        
		return totalTitulo;
	}
	
	
	public void setNossonumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
		
	}
	

}
