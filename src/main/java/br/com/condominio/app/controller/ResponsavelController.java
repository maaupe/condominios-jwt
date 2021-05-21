package br.com.condominio.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.condominio.app.controller.dto.ResponsavelDto;
import br.com.condominio.app.message.response.Response;
import br.com.condominio.app.modelo.service.IResponsavelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api ( value = "API Responsaveis" ) 
@RequestMapping("/api/responsaveis")
@RestController
public class ResponsavelController {

	@Autowired
	private IResponsavelService responsavelService;
	
	@ApiOperation(value = "Retorna uma lista de responsaveis")  
	@GetMapping(value = "/listar-todos")
	public ResponseEntity<Response<List<ResponsavelDto>>> listarResponsaveis(){
	System.out.println("********** chamou listar todos os responsaveis ***************");
	
		Response<List<ResponsavelDto>> response = new Response<>();
		response.setData(responsavelService.listarTodos());
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(ResponsavelController.class).listarResponsaveis()).withSelfRel());
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@ApiOperation(value = "Cadastrar um novo Responsavel")  
	@PostMapping(value="/novo")
	public ResponseEntity<Response<Boolean>> cadastrarRespoonsavel(@RequestBody ResponsavelDto responsavel){
		Response<Boolean> response = new Response<>();
		response.setData(responsavelService.salvar(responsavel));
		response.setStatusCode(HttpStatus.OK.value());
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@ApiOperation(value = "Busca um Responsavel pelo identificador")  
	@GetMapping(value="/obterPorId/{idResponsavel}")
	public ResponseEntity<Response<ResponsavelDto>> obterPorId(@PathVariable Long idResponsavel){
					
		Response<ResponsavelDto> response = new Response<>();
		response.setData( this.responsavelService.buscaPorId(idResponsavel) );
		response.setStatusCode(HttpStatus.OK.value());
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	
	@ApiOperation(value = "Altera responsavel")  
	@PutMapping(value="/atualiza")
	public ResponseEntity<Response<Boolean>> alterarResponsavel(@RequestBody ResponsavelDto responsavel){
		
		Response<Boolean> response = new Response<>();
		response.setData(responsavelService.atualizar(responsavel));
		response.setStatusCode(HttpStatus.OK.value());
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		
		
	}
	
	
	
	
	
	
	
	
}
