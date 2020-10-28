package br.com.condominio.app.modelo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.condominio.app.modelo.Condominio;

public interface ICondominioService {
	
	public void salvar(Condominio condominio);
	public void deletarPorid(Long idCondominio);
	public void deletar(Condominio condominio) ;
	public List<Condominio> buscaPorNomeContendo(String nome);
	public void alterar(Condominio condominio);
	public List<Condominio> buscaPorNomecomecandoPorIgnorandoMaisculo(String nome);
	public Condominio BuscaPorId(Long idCondominio);
	public List<Condominio> buscaTodos();
	public Page<Condominio> BuscaTodosPaginados(Pageable pageable);

}
