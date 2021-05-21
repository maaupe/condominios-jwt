package br.com.condominio.app.modelo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.condominio.app.constant.MensagensConstant;
import br.com.condominio.app.controller.dto.ResponsavelDto;
import br.com.condominio.app.controller.dto.UnidadeDto;
import br.com.condominio.app.exception.ResponsavelException;
import br.com.condominio.app.modelo.Responsavel;
import br.com.condominio.app.modelo.repository.IResponsavelRepository;
import br.com.condominio.app.modelo.service.IResponsavelService;

@Service
public class ResponsavelServiceImpl implements IResponsavelService {


	private IResponsavelRepository  responsavelRepository;
	private ModelMapper mapper;
	
	@Autowired
	public ResponsavelServiceImpl(IResponsavelRepository responsavelRepository) {
		this.responsavelRepository = responsavelRepository;
		this.mapper = new ModelMapper();
	}
	
	@Override
	public Boolean salvar(ResponsavelDto responsavel) {
		try {
			Responsavel responsavelIn= mapper.map(responsavel, Responsavel.class);
			this.responsavelRepository.save(responsavelIn);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deletar(Long idresponsavel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean atualizar(ResponsavelDto responsavel) {
		
		try {
			this.responsavelRepository.buscadPorId(responsavel.getId());
			return cadastrarOuAtualizar(responsavel);
		} catch (ResponsavelException r) {
			throw r;
		}

		
	}

	@Override
	public ResponsavelDto buscaPorId(Long idResponsavel) {

		try {
			return this.mapper.map(this.responsavelRepository.buscadPorId(idResponsavel), new TypeToken<ResponsavelDto>(){}.getType());
		}catch (ResponsavelException u) {
			throw new ResponsavelException(MensagensConstant.ERRO_UNIDADE_NAO_ENCONTRADA.getValor(), HttpStatus.NOT_FOUND);
		}catch (Exception e) {
     		System.out.println(e.getMessage());
		}
		return null;

	}

	@Override
	public List<ResponsavelDto> listarTodos() {
		try {
            return this.mapper.map(this.responsavelRepository.findAll(), new TypeToken<List<ResponsavelDto>>(){}.getType());			
		} catch (Exception e) {
			throw new ResponsavelException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	private Boolean cadastrarOuAtualizar(ResponsavelDto responsavel) {
		Responsavel responsavelEnt = this.mapper.map(responsavel, Responsavel.class);
		this.responsavelRepository.save(responsavelEnt);
		return Boolean.TRUE;
	}
}
