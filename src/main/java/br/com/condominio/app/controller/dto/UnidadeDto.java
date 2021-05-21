package br.com.condominio.app.controller.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.condominio.app.modelo.Condominio;
import br.com.condominio.app.modelo.TipoUnidade;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
//@Table(name = "vwUnidade")
public class UnidadeDto {
	@Id
	private Long id;
		
	private String numero;
		
	private TipoUnidade tipounidade;
	
    private boolean garagem;
    
    private Long responsavelId;	
    
    private String nomeresponsavel;
    
    private Long condominioId;
    
    private String razaoSocial; 
    
    public boolean getTemGaragem(){ 
    	if (garagem){ 
    		return true; 
    	} else { 
    		return false; 
    	} 
    }
}
