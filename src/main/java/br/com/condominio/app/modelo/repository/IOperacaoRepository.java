package br.com.condominio.app.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.condominio.app.modelo.Operacao;

@Repository
public interface IOperacaoRepository extends JpaRepository<Operacao, Long> {

}
