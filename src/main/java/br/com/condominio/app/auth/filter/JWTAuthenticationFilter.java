package br.com.condominio.app.auth.filter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.condominio.app.auth.service.JWTService;
import br.com.condominio.app.auth.service.JWTServiceImpl;
import br.com.condominio.app.modelo.Usuario;
import br.com.condominio.app.modelo.dto.UsuarioDto;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		this.jwtService = jwtService;
	}


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		if(username != null && password !=null) {
			logger.info("Username desde request parameter (form-data): " + username);
			logger.info("Password desde request parameter (form-data): " + password);
			
		} else {
			Usuario user = null;
			try {
				
				user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
				
				username = user.getUsername();
				password = user.getPassword();
				
				logger.info("Username desde request InputStream (raw): " + username);
				logger.info("Password desde request InputStream (raw): " + password);
				
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		username = username.trim();
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authToken);
    }
    
    
    protected void successfulAuthentication(HttpServletRequest request, 
    		                                HttpServletResponse response, FilterChain filterChain, Authentication authResult) throws IOException, ServletException  {
    
		String token = jwtService.create(authResult);
		 
		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		
		UsuarioDto usuariodto = new UsuarioDto();
		
		//usuariodto = (UsuarioDto)c;
		usuariodto.setUsername(((User) authResult.getPrincipal()).getUsername());
		usuariodto.setToken(token);
		usuariodto.setAccountNonExpired( ((User) authResult.getPrincipal()).isAccountNonExpired());
		
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((User) authResult.getPrincipal()).getAuthorities() ;
		
		usuariodto.setAuthorities(authorities);
		//usuariodto.setAuthorities( (List<GrantedAuthority>) ((User) authResult.getPrincipal()).getAuthorities()  );
		
		
		body.put("success", true);
		//body.put("username", ((User) authResult.getPrincipal()).getUsername()) ;
		//body.put("token", token); colocar o token na classe usuario e nao persistir
	      
		body.put("data", usuariodto );
		
		System.out.println(body);
		
		response.setContentType("application/jsonj;charset=UTF-8");
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
        
    }
    
    
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		body.put("success", false);
		
		response.setContentType("application/jsonj;charset=UTF-8");
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		

	}
    
    
    
    
    
    
}