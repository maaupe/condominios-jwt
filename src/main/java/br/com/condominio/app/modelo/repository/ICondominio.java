package br.com.condominio.app.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.condominio.app.controller.dto.CondominioDto;
import br.com.condominio.app.modelo.Condominio;

@Repository
public interface ICondominio extends PagingAndSortingRepository<Condominio, Long> {
	
	@Query("Select c from Condominio c where c.diaVencimento=:diaVencimento")
	public List<Condominio> findByDiaVenciento(@Param("diaVencimento") int diaVencimento);
		
	@Query("Select c from Condominio c where c.id between :idCondIni and :idCondFinal")
	public List<Condominio> findByFiltroId(@Param("idCondIni") Long idCondIni, @Param("idCondFinal") Long idCondFinal );

}
