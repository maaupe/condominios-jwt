package br.com.condominio.app.modelo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.condominio.app.constant.MensagensConstant;
import br.com.condominio.app.controller.dto.ContaDto;
import br.com.condominio.app.exception.ResponsavelException;
import br.com.condominio.app.modelo.Conta;
import br.com.condominio.app.modelo.repository.IContaRepository;
import br.com.condominio.app.modelo.service.IContaService;

@Service
public class ContaServiceImpl implements IContaService {

	private IContaRepository  contaRepository;
	private ModelMapper mapper;
	
	@Autowired
	public ContaServiceImpl(IContaRepository contaRepository) {
		this.contaRepository = contaRepository;
		this.mapper = new ModelMapper();
	}
	
	
	@Override
	public Boolean salvar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deletar(Long idConta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean atualizar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conta buscaPorId(Long idConta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaDto> listarTodas() {
		try {
            return this.mapper.map(this.contaRepository.findAll(), new TypeToken<List<ContaDto>>(){}.getType());			
		} catch (Exception e) {
			throw new ResponsavelException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Page<ContaDto> listarTodas(Pageable pageable) {
		try {
            return this.mapper.map(this.contaRepository.findAll(pageable) , new TypeToken<Page<ContaDto>>(){}.getType());			
		} catch (Exception e) {
			throw new ResponsavelException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
