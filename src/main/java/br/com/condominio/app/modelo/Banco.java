package br.com.condominio.app.modelo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class Banco {

     private Long id;
     private String numero;
     private String agencia;
     private String contacorrente;
     private String nome;
     private String codigodaEmpresa;
	 private BigDecimal saldo; 
     
     public Banco(String numero,String agencia, String contacorrente, String nome, String codigodaEmpresa) {
    	 this.numero =numero;
    	 this.agencia = agencia;
    	 this.contacorrente = contacorrente;
    	 this.nome =nome;
    	 this.codigodaEmpresa = codigodaEmpresa;
    		 
     }
	
}
