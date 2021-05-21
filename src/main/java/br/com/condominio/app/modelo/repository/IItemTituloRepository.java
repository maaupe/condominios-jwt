package br.com.condominio.app.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.condominio.app.modelo.ItemTitulo;

public interface IItemTituloRepository extends JpaRepository<ItemTitulo, Long> {
	
	
}
