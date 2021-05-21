package br.com.condominio.app.modelo.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.condominio.app.controller.dto.OperacaoDto;
import br.com.condominio.app.modelo.Operacao;

public interface IOperacaoService {
	public Boolean salvar(Operacao servico);
	public void deletarPorid(Long idServico);
	public Boolean alterar(Operacao item);
	public Operacao buscarPorId(Long idServico);
	public List<OperacaoDto> buscarTodos();
	public Page<OperacaoDto> listarTodas(Pageable pageable);
}
