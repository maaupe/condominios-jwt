package br.com.condominio.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.condominio.app.modelo.Role;
import br.com.condominio.app.modelo.Usuario;
import br.com.condominio.app.modelo.service.impl.UsuarioServiceImpl;

@EnableCaching
@SpringBootApplication
public class CondominiosApplication extends SpringBootServletInitializer{
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CondominiosApplication.class);
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CondominiosApplication.class, args);
	}
	
	
	@Autowired
 	UsuarioServiceImpl usuarioService;
	
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostConstruct
	@Transactional
	public void onLoad() {
		
		Usuario usuario = new Usuario();
		Role role = new Role();
		List<Role> roles= new ArrayList<>();
		role.setDescricao("ADMIN");
		roles.add(role);
		usuario.setUsername("maaupe");
		usuario.setEmail("maaupe@gmail.com");
		usuario.setName("Marcos");
        //"enabled": true,
		usuario.setPassword(encoder.encode("123456"));
		usuario.setRoles(roles);
		if (!usuarioService.existsByUsername("maaupe")) { 
			usuarioService.Salvar(usuario);
		}
		
	}
}
