package br.com.condominio.app.modelo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.condominio.app.controller.dto.CondominioDto;
import br.com.condominio.app.modelo.Condominio;

public interface ICondominioService {
	
	/*public List<Condominio> buscaPorNomeContendo(String nome);
	public List<Condominio> buscaPorNomecomecandoPorIgnorandoMaisculo(String nome);
	public Page<Condominio> BuscaTodosPaginados(Pageable pageable);*/
	
	public Boolean salvar(CondominioDto condominio);
	public Boolean deletar(Long idCondominio);
	public Boolean atualizar(CondominioDto condominio);
	public CondominioDto BuscaPorId(Long idCondominio);
	public List<CondominioDto> buscaTodos();
	//public Page<CondominioDto> BuscaTodosPaginados(Pageable pageable);
		
	public List<CondominioDto> buscaDiaVenciento(int diaVencimento);
	public List<CondominioDto> findByFiltro(Long idCondIni, Long idCondFinal );
	
	

}
