package br.com.condominio.app.modelo.service.impl;


import br.com.condominio.app.exception.SenhaInvalidaException;
import br.com.condominio.app.modelo.Role;
import br.com.condominio.app.modelo.Usuario;
import br.com.condominio.app.modelo.repository.IRoleRepository;
import br.com.condominio.app.modelo.repository.IUsuarioRepository;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Autowired
    private IRoleRepository roleRepository; 

    @Transactional
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public UserDetails autenticar( Usuario usuario ){
        UserDetails user = loadUserByUsername(usuario.getUsername());
        boolean senhasBatem = encoder.matches( usuario.getPassword(), user.getPassword() );

        if(senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    	Usuario usuario =  usuarioRepository.findByUsername(username);

        List<Role> roles = usuario.getRoles();
        
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role:roles) {
        	authorities.add((GrantedAuthority) new SimpleGrantedAuthority(role.getDescricao()));
        }
        
        UsuarioCustom usuarioCustom = new UsuarioCustom();
        usuarioCustom.setUsername(usuario.getUsername());
        usuarioCustom.setUsuario(usuario);
        usuarioCustom.setAuthorities(authorities);
        
        return usuarioCustom;
    }
    
    public Boolean existsByUsername(String userName){
    	return usuarioRepository.existsByUsername(userName);
    	
    }
    
    public void Salvar(Usuario usuario) {
    	usuarioRepository.save(usuario) ;
 
    }
}