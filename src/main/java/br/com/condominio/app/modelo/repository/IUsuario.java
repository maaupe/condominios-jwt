package br.com.condominio.app.modelo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.condominio.app.modelo.Usuario;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
	
	public Usuario findByUsernameOrEmail(String username, String email);
	
	public Boolean existsByUsername(String username);
}