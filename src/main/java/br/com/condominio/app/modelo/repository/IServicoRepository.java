package br.com.condominio.app.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.condominio.app.modelo.Operacao;

public interface IServicoRepository extends JpaRepository<Operacao, Long> {

}
