package br.com.condominio.app.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.condominio.app.modelo.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
		
	 Usuario findByUsername(String username);
	 Boolean existsByUsername(String username);
	 
}