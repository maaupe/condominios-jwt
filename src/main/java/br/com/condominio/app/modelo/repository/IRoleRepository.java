package br.com.condominio.app.modelo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.condominio.app.modelo.Role;

public interface IRoleRepository extends JpaRepository<Role, Long>{

	Role findByDescricao(String descricao);
	Role save(Role role);
		
}
