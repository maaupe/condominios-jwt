package br.com.condominio.app.modelo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.condominio.app.modelo.Condominio;

@Repository
public interface ICondominio extends PagingAndSortingRepository<Condominio, Long> {
	
}
