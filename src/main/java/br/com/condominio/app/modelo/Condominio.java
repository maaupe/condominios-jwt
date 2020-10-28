package br.com.condominio.app.modelo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
public class Condominio implements Serializable {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long id;
	
	   @NotEmpty(message ="nao pode ser vazio")
	   @Size(min=10, max=50, message="o tamanho tem que estar entre 10 e 50")
	   @Column(nullable=false)
	   private String sindico;
       
       @Column(name="razaosocial", nullable = false)
       private String razaoSocial;
       
       @Column(nullable = false)
       private String cnpj;
   	   
       @DateTimeFormat (iso = ISO.DATE)
   	   @Column(name="datacadastro")
       private LocalDate dataCadastro;
       
       @Column(nullable = false)
       private String status;
	
	public Condominio() {
	
	}
	
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getSindico() {
		return sindico;
	}
	
	public void setSindico(String sindico) {
		this.sindico = sindico;
	}
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condominio other = (Condominio) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		return true;
	}

	
	
	
}
