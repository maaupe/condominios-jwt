package br.com.condominio.app.modelo.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import br.com.condominio.app.modelo.ItemTitulo;
import br.com.condominio.app.modelo.Titulo;

public interface IItemTituloService {
	public Boolean salvar(ItemTitulo itemTitulo);
	public void deletarPorid(Long idItemtulo);
	public Titulo alterar(ItemTitulo itemTitulo);
	public Titulo BuscaPorId(Long idItemTitulo);
	public List<ItemTitulo> buscaTodos();
	public Page<ItemTitulo> BuscaTodosPaginados(Pageable pageable);
}
