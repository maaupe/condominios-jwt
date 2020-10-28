package br.com.condominio.app.controller;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.condominio.app.modelo.Usuario;

//@CrossOrigin
@RestController
@RequestMapping("/token")
public class UsuarioController {
	
	/*@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            authenticationRequest.getEmail(), 
            authenticationRequest.getPassword())
			 );
	
        	
		} catch (BadCredentialsException e) {
			
			throw new Exception("Login ou senha incorretos", e);

		} 

		final UserDetails usuario = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		return ResponseEntity.ok(jwtGenerator.generate(usuario));
	}*/



   /* @PostMapping
    public String generate(@RequestBody final Usuario jwtUser) {
        System.out.println( "entrou no controller generate ");
        return jwtGenerator.generate(jwtUser);

    }*/
	/*
    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authenticationRequest ) throws Exception {
        
    	String token="";
    	  
    	try {
    		Authentication authenticate = authenticationManager.authenticate(
                                          new UsernamePasswordAuthenticationToken(
                                        		  authenticationRequest.getUsername(), 
                                        		  authenticationRequest.getPassword()));
            System.out.println();
            final UserDetails userDetails = userDetailsService
    				.loadUserByUsername(authenticationRequest.getUsername());

             token = jwtGenerator.generate(userDetails);
            
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        
    	return ResponseEntity.ok(new JwtResponse(token));
    }
    
    */
	
	//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> saveUser(@RequestBody UsuarioDTO user) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(user));
//	}
/*
    @PostMapping("/nova-conta")
    public ResponseEntity<?> create(@RequestBody  AuthRequest authenticationRequest) throws Exception {
        
    	System.out.println(authenticationRequest.getPassword());
    	
    	//String username = body.get("username");
        String username = authenticationRequest.getUsername();
        if (userDetailsService.existsByUsername(username)){
            throw new ValidationException("Nome de usuario ja existe");
        }

        String password = authenticationRequest.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String email = authenticationRequest.getUsername();
//        String hashedPassword = hashData.get_SHA_512_SecurePassword(password);
        userDetailsService.salvar(new Usuario(username, encodedPassword, "ADMIN", email ));
        
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        		); 
        final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
 		final String token = jwtGenerator.generate(userDetails);
 		
 		return ResponseEntity.ok( new JwtResponse(token) );

    }*/
    
	/*
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}*/
}
