package br.com.condominio.app.controller;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.condominio.app.controller.dto.CredenciaisDTO;
import br.com.condominio.app.message.response.JwtResponse;
import br.com.condominio.app.message.response.Response;
import br.com.condominio.app.modelo.Role;
import br.com.condominio.app.modelo.Usuario;
import br.com.condominio.app.modelo.UsuarioRequest;
import br.com.condominio.app.modelo.repository.IRoleRepository;
import br.com.condominio.app.modelo.service.impl.UsuarioCustom;
import br.com.condominio.app.modelo.service.impl.UsuarioServiceImpl;
import br.com.condominio.app.security.jwt.JwtProvider;
import io.swagger.annotations.Api;

@Api ( value = "API Descricao" ) 
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioServiceImpl usuarioService; 
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity salvar(@Valid @RequestBody UsuarioRequest credenciais) {
		
		if(usuarioService.existsByUsername(credenciais.getUsername())) {
			return new ResponseEntity("Username ja existe!", HttpStatus.BAD_REQUEST);
			
		}
		 /*
		  * novo usuario
		  */
		Usuario usuario = new Usuario();
		usuario.setName(credenciais.getName());
		usuario.setUsername(credenciais.getUsername()); 
		usuario.setPassword(encoder.encode(credenciais.getPassword()));  
		
		List<Role> roles = credenciais.getRoles();
		roles.forEach(role-> {
			Role role1 = roleRepository.findByDescricao(role.getDescricao());
			if (role1!=null) {
				role.setId(role1.getId());
			}
		});

		roleRepository.saveAll(roles);
		usuario.setRoles(roles);
		usuarioService.salvar(usuario);
		return  ResponseEntity.ok().body("Usuario registrado com Sucesso !!!");
	}

	@PostMapping("/login")
	public @ResponseBody 
	ResponseEntity<Response<?>> autenticar(@Valid @RequestBody CredenciaisDTO credenciais) throws JsonProcessingException{
		
		Response<JwtResponse> response = new Response<>();
		
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credenciais.getUsername(),
                        credenciais.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        
        UsuarioCustom usuario = (UsuarioCustom) authentication.getPrincipal();
        
		List<String> roles = usuario.getAuthorities().stream()
		                     .map(item -> item.getAuthority())
		                     .collect(Collectors.toList());
        
		response.setData(new JwtResponse(jwt, credenciais.getUsername(), roles));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).autenticar(credenciais)).withSelfRel());
		
        return ResponseEntity.ok(response);		
		
		
		
	}
	
	/*public @ResponseBody ResponseEntity<?> autenticar(@Valid @RequestBody CredenciaisDTO credenciais) throws JsonProcessingException{
        
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credenciais.getUsername(),
                        credenciais.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        
        UsuarioCustom usuario = (UsuarioCustom) authentication.getPrincipal();
        
		List<String> roles = usuario.getAuthorities().stream()
		                     .map(item -> item.getAuthority())
		                     .collect(Collectors.toList());
        
        return ResponseEntity.ok(new JwtResponse(jwt, credenciais.getUsername(), roles));
    }*/
	
	
}
