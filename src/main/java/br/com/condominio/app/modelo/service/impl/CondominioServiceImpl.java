package br.com.condominio.app.modelo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.condominio.app.modelo.Condominio;
import br.com.condominio.app.modelo.repository.ICondominio;
import br.com.condominio.app.modelo.service.ICondominioService;

@Service
public class CondominioServiceImpl implements ICondominioService  {

	@Autowired
	ICondominio  condominioRepositorio;
	
	@Override
	@Transactional
	public void salvar(Condominio condominio) {
		
		condominioRepositorio.save(condominio);
	}

	@Override
	public void deletarPorid(Long idCondominio) {
		
		
	}

	@Override
	@Transactional
	public void deletar(Condominio condominio) {
		condominioRepositorio.delete(condominio);
	}

	@Override
	public List<Condominio> buscaPorNomeContendo(String nome) {
		
		return null;
	}

	@Override
	public List<Condominio> buscaPorNomecomecandoPorIgnorandoMaisculo(String nome) {
		
		return null;
	}

	@Override
	@Transactional
	public Condominio BuscaPorId(Long idCondominio) {
		return condominioRepositorio.findById(idCondominio).get() ;
	}

	@Override
	public List<Condominio> buscaTodos() {
		List<Condominio> condominios = (List<Condominio>) condominioRepositorio.findAll();
		return condominios;
	}

	@Override
	public Page<Condominio> BuscaTodosPaginados(Pageable pageable) {
		
		return condominioRepositorio.findAll(pageable);
	}

	@Override
	@Transactional
	public void alterar(Condominio condominio) {
		
		condominioRepositorio.save(condominio);
	}
	
	
}
