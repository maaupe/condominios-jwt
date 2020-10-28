package br.com.condominio.app.modelo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.condominio.app.modelo.Role;
import br.com.condominio.app.modelo.Usuario;
import br.com.condominio.app.modelo.repository.IUsuario;


@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuario usuarioDao;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null) {
        	logger.error("Erro no Login: não existe o usuario '" + username + "' no sistema!");
        	throw new UsernameNotFoundException("Username: " + username + " não existe no sistema!");
        }
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        for(Role role: usuario.getRoles()) {
        	logger.info("Role: ".concat(role.getAuthority()));
        	authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        
        if(authorities.isEmpty()) {
        	logger.error("Erro no Login: Usuario '" + username + "' não tem roles atribuidos!");
        	throw new UsernameNotFoundException("Error no Login: usuario '" + username + "' nao tem roles atribuidos!");
        }
        
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
