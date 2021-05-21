package br.com.condominio.app.modelo.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.condominio.app.controller.dto.ResponsavelDto;
import br.com.condominio.app.modelo.Responsavel;

public interface IResponsavelRepository extends  PagingAndSortingRepository<Responsavel, Long>{
	
	@Query(value=" SELECT r FROM Responsavel r where r.id = :idResponsavel")
	public Responsavel buscadPorId(@Param("idResponsavel") Long idResponsavel);
	

}
