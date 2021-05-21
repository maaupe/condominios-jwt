package br.com.condominio.app.modelo.service;

import java.util.List;

import br.com.condominio.app.controller.dto.ResponsavelDto;
import br.com.condominio.app.controller.dto.UnidadeDto;
import br.com.condominio.app.modelo.Responsavel;


public interface IResponsavelService {
	
	public Boolean salvar(ResponsavelDto responsavel);
	public Boolean deletar(Long idresponsavel);
	public Boolean atualizar(ResponsavelDto responsavelDto);
	public ResponsavelDto buscaPorId(Long idResponsavel);
	public List<ResponsavelDto> listarTodos();
	

}
