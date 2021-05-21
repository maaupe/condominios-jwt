package br.com.condominio.app.modelo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.condominio.app.modelo.Endereco;
import br.com.condominio.app.modelo.repository.IEnderecoRepository;
import br.com.condominio.app.modelo.service.IEnderecoService;

@Service
public class EnderecoServiceImpl implements IEnderecoService  {

	@Autowired
	private IEnderecoRepository enderecoRepository;
	
	
	
	@Override
	public void salvar(Endereco endereco) {
		// TODO Auto-generated method stub
	}

	@Override
	@Transactional
	public Endereco alterar(Endereco endereco) {
		return enderecoRepository.save(endereco) ;
	}

	@Override
	public void deletarPorid(Long idEndereco) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Endereco BuscaPorId(Long idEndereco) {
		// TODO Auto-generated method stub
		return null;
	}

}
