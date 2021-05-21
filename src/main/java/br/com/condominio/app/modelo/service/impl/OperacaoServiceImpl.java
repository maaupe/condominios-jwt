package br.com.condominio.app.modelo.service.impl;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.condominio.app.constant.MensagensConstant;
import br.com.condominio.app.controller.dto.OperacaoDto;
import br.com.condominio.app.exception.OperacaoException;
import br.com.condominio.app.exception.ResponsavelException;
import br.com.condominio.app.modelo.Operacao;
import br.com.condominio.app.modelo.repository.IOperacaoRepository;
import br.com.condominio.app.modelo.service.IOperacaoService;


@Service
public class OperacaoServiceImpl implements IOperacaoService {

	private IOperacaoRepository operacaoRepository;
	private ModelMapper mapper;
	
	public OperacaoServiceImpl(IOperacaoRepository operacaoRepository) {
		this.operacaoRepository =  operacaoRepository;
		this.mapper = new ModelMapper();
	}
	
	@Override
	public Boolean salvar(Operacao servico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorid(Long idServico) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean alterar(Operacao item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operacao buscarPorId(Long idServico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperacaoDto> buscarTodos() {

		try {
    	   return this.mapper.map(this.operacaoRepository.findAll(), new TypeToken<List<OperacaoDto>>(){}.getType());
       } catch (Exception e) {
    	   
    	   System.out.println(e.getMessage());
    	   throw new OperacaoException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR );
       }
		
	}
	
	
	@Override
	public Page<OperacaoDto> listarTodas(Pageable pageable) {
		try {
            return this.mapper.map(this.operacaoRepository.findAll(pageable), new TypeToken<Page<OperacaoDto>>(){}.getType());			
		} catch (Exception e) {
			throw new ResponsavelException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	
}
