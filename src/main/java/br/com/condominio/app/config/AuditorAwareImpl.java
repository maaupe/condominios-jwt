package br.com.condominio.app.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
    	
    	//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	//System.out.println("************** Usuario : "+((User) auth.getPrincipal()).getUsername());
        return Optional.of("Marcos");
        // Can use Spring Security to return currently logged in user
    	
        // return Optional.of( ((User) auth.getPrincipal()).getUsername());
 
    }
}

