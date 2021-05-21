package br.com.condominio.app.modelo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.condominio.app.controller.dto.UnidadeDto;
import br.com.condominio.app.modelo.Unidade;

public interface IUnidadeService {
	
	public Boolean salvar(UnidadeDto unidade);
	public Boolean deletar(Long idUnidade);
	public Boolean atualizar(UnidadeDto unidade);
	public UnidadeDto buscaPorId(Long idUnidade);
	public List<UnidadeDto> listarTodas();
	public List<UnidadeDto> buscaUnidadePorCondominio(Long IdCondominio);
	/*public Unidade salvar(Unidade unidade);
	public void deletarPorid(Long idUnidade);
	public void deletar(Unidade unidade) ;
	public List<Unidade> buscaPorNomeContendo(String nome);
	public void alterar(Unidade unidade);
	public List<Unidade> buscaPorNomecomecandoPorIgnorandoMaisculo(String nome);
	public Unidade BuscaPorId(Long idunidade);
	public Unidade BuscaPorNumero(String numero);
	public List<Unidade> buscaTodos();
	public Page<Unidade> BuscaTodosPaginados(Pageable pageable);
    public List<Unidade> buscaUnidadePorCondominio(Long IdCondominio);*/
    
    
    
    
    
    
    
}
