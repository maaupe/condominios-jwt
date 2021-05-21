package br.com.condominio.app.modelo.service.impl;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import br.com.condominio.app.modelo.ItemTitulo;
import br.com.condominio.app.modelo.Titulo;
import br.com.condominio.app.modelo.repository.IItemTituloRepository;
import br.com.condominio.app.modelo.service.IItemTituloService;

public class ItemTituloServiceImpl implements IItemTituloService{

	@Autowired
	private IItemTituloRepository itemTituloRepository;
	
	@Override
	public Boolean salvar(ItemTitulo itemTitulo) {
		try {
			this.itemTituloRepository.save(itemTitulo);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	

	@Override
	public void deletarPorid(Long idItemtulo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Titulo alterar(ItemTitulo itemTitulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Titulo BuscaPorId(Long idItemTitulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemTitulo> buscaTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ItemTitulo> BuscaTodosPaginados(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


}
