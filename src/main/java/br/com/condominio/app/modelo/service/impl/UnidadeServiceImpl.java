package br.com.condominio.app.modelo.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.condominio.app.constant.MensagensConstant;
import br.com.condominio.app.controller.dto.UnidadeDto;
import br.com.condominio.app.exception.UnidadeException;
import br.com.condominio.app.modelo.Unidade;
import br.com.condominio.app.modelo.repository.IUnidadeRepository;
import br.com.condominio.app.modelo.service.IUnidadeService;


@CacheConfig(cacheNames="unidade")
@Service
public class UnidadeServiceImpl implements IUnidadeService {

	private IUnidadeRepository unidadeRepository;
	private ModelMapper mapper;

	@Autowired
	public UnidadeServiceImpl(IUnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
		this.mapper = new ModelMapper();
	}

	@Override
	public Boolean salvar(UnidadeDto unidade) {
		try {
			Unidade unidadeEnt = mapper.map(unidade, Unidade.class);
			this.unidadeRepository.save(unidadeEnt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deletar(Long idUnidade) {
		try {
			this.buscaPorId(idUnidade);
			this.unidadeRepository.deleteById(idUnidade);
			return Boolean.TRUE;
		} catch (UnidadeException m) {
			throw m;
		} catch (Exception e) {
			throw new UnidadeException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

	@Override
	public Boolean atualizar(UnidadeDto unidade) {

		try {
			this.buscaPorId(unidade.getId());
			return this.cadastrarOuAtualizar(unidade);
		} catch (UnidadeException m) {
			throw m;
		} catch (Exception e) {
			throw new UnidadeException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

	@Override
	public UnidadeDto buscaPorId(Long idUnidade) {
		try {
			return this.mapper.map(this.unidadeRepository.buscadPorId(idUnidade), new TypeToken<UnidadeDto>(){}.getType());
		} catch (UnidadeException u) {
			throw new UnidadeException(MensagensConstant.ERRO_UNIDADE_NAO_ENCONTRADA.getValor(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			throw new UnidadeException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//@CachePut(unless="#result.size()<3")
	@Override
	public List<UnidadeDto> listarTodas() {
		try {
            return this.mapper.map(this.unidadeRepository.todasAsUnidades(), new TypeToken<List<UnidadeDto>>(){}.getType());			
		} catch (Exception e) {
			throw new UnidadeException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	private Boolean cadastrarOuAtualizar(UnidadeDto unidade) {
		Unidade unidadeEnt = this.mapper.map(unidade, Unidade.class);
		this.unidadeRepository.save(unidadeEnt);
		return Boolean.TRUE;
	}
	
	
	@Override
	public List<UnidadeDto> buscaUnidadePorCondominio(Long IdCondominio) {
		try {
			return this.mapper.map(this.unidadeRepository.findByCondominioId(IdCondominio), new TypeToken<List<UnidadeDto>>(){}.getType());
		}catch  (Exception e){
			e.getMessage();
			throw new UnidadeException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}	

}
