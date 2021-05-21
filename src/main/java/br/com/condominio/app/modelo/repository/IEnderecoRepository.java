package br.com.condominio.app.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.condominio.app.modelo.Endereco;

@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
	
}