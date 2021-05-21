package br.com.condominio.app.modelo.service.impl;

import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.condominio.app.controller.dto.CondominioDto;
import br.com.condominio.app.exception.CondominioException;
import br.com.condominio.app.modelo.Condominio;
import br.com.condominio.app.modelo.repository.ICondominio;
import br.com.condominio.app.modelo.service.ICondominioService;

@CacheConfig(cacheNames="condominio")
@Service
public class CondominioServiceImpl implements ICondominioService  {

    private static final String CONDOMINIO_NAO_ENCONTRADO = " Erro  Condominio não encontrado !!!";
    private static final String MENSAGEM_ERRO = "Erro interno. Contate o Suporte";
	private ICondominio  condominioRepositorio;
	private ModelMapper mapper;	
	
	@Autowired
	public CondominioServiceImpl(ICondominio condominioRepositorio) {
	    this.mapper = new ModelMapper();
		this.condominioRepositorio = condominioRepositorio;
		
	}

	@Override
	public Boolean salvar(CondominioDto condominio) {
		try {
			Condominio condominioEnt = mapper.map(condominio, Condominio.class);
			this.condominioRepositorio.save(condominioEnt);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deletar(Long idCondominio) {
		try {
			this.BuscaPorId(idCondominio);
			this.condominioRepositorio.deleteById(idCondominio);
			return true;
		} catch (Exception e) {
          return false;			
		}
	}

	@Override
	public Boolean atualizar(CondominioDto condominio) {
		try {
			this.BuscaPorId(condominio.getId());
			this.cadastrarOuAtualizar(condominio);
			return true;
		} catch (CondominioException c) {
			throw c;
		} catch (Exception e) {
			throw e;
		}
	}

	//@CachePut(unless="#result.size()<3")
	@Override
	public CondominioDto BuscaPorId(Long idCondominio) {
		try {
			Optional<Condominio> condominioOptional = this.condominioRepositorio.findById(idCondominio);
			if(condominioOptional.isPresent()) {
				
				return this.mapper.map(condominioOptional.get(), CondominioDto.class);
			}
			throw new CondominioException(CONDOMINIO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
		} catch (CondominioException c) {
			throw c;
		} catch (Exception e) {
            throw new CondominioException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//@CachePut(unless="#result.size()<3")
	@Override
	public List<CondominioDto> buscaTodos() {
		try {
            return this.mapper.map(this.condominioRepositorio.findAll(), new TypeToken<List<CondominioDto>>(){}.getType());			
		} catch (Exception e) {
			throw new CondominioException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public List<CondominioDto> buscaDiaVenciento(int diaVencimento) {
		try {
			return this.mapper.map(this.condominioRepositorio.findByDiaVenciento(diaVencimento), new TypeToken<List<CondominioDto>>(){}.getType());
		}catch (Exception e) {
			throw new CondominioException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<CondominioDto> findByFiltro(Long idCondIni, Long idCondFinal) {
		try {
			return this.mapper.map(this.condominioRepositorio.findByFiltroId(idCondIni, idCondFinal) , new TypeToken<List<CondominioDto>>(){}.getType());
		}catch (Exception e) {
			throw new CondominioException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	private Boolean cadastrarOuAtualizar(CondominioDto condominio) {
		Condominio condominioEnt = this.mapper.map(condominio, Condominio.class);
		this.condominioRepositorio.save(condominioEnt);
		return Boolean.TRUE;
	}
	
}
