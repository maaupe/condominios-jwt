package br.com.condominio.app.modelo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.condominio.app.controller.dto.ContaDto;
import br.com.condominio.app.modelo.Conta;

public interface IContaService {

	public Boolean salvar();
	public Boolean deletar(Long idConta);
	public Boolean atualizar();
	public Conta buscaPorId(Long idConta);
	public List<ContaDto> listarTodas();
	public Page<ContaDto> listarTodas(Pageable pageable);
	
	
}
