package br.com.condominio.app.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.condominio.app.modelo.Conta;

@Repository
public interface IContaRepository extends JpaRepository<Conta, Long>{

}
