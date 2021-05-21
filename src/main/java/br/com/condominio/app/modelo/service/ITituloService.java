package br.com.condominio.app.modelo.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.condominio.app.controller.dto.TituloDto;
import br.com.condominio.app.modelo.Titulo;
import br.com.condominio.app.modelo.filtro.BoletoFilter;
import br.com.condominio.app.modelo.filtro.RemessaFilter;
import br.com.condominio.app.modelo.filtro.TituloFilter;

public interface ITituloService {
	public Titulo salvar(Titulo titulo);
	public void deletarPorid(Long idTitulo);
	public void deletar(Titulo titulo) ;
	public List<TituloDto> buscaPorNomeContendo(String nome);
	//public List<Titulo> buscaPoridCondominioEvencimento(Long condominioId, LocalDate vencimento);
	public Titulo alterar(Titulo titulo);
	public List<TituloDto> buscaPorNomecomecandoPorIgnorandoMaisculo(String nome);
	public TituloDto BuscaPorId(Long idTitulo);
	public Page<TituloDto> buscarTodos(Pageable pageable);
	public List<TituloDto> buscarTodos();
	public Titulo baixarTitulo(int Numero);
	public String gerarTitulo(TituloFilter filtro);
	public File gerarBoletoPdf(BoletoFilter filtro) throws IOException;
	public String gerarRemessa(RemessaFilter filtro) throws IOException;
	
	
	
    
}
