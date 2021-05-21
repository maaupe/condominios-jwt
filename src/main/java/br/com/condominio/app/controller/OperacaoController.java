package br.com.condominio.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import br.com.condominio.app.controller.dto.OperacaoDto;
import br.com.condominio.app.message.response.Response;
import br.com.condominio.app.modelo.service.IOperacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api ( value = "API Operacaoes" ) 
@RequestMapping("/api/operacoes")
@RestController
public class OperacaoController {

	
	@Autowired
	private IOperacaoService operacaoService;
	
	
	@ApiOperation(value = "Retorna uma lista de contas")  
	@GetMapping(value = "/listar-todas")
	public ResponseEntity<Response<List<OperacaoDto>>> listarOperacoes() throws Exception {
		
		Response<List<OperacaoDto>> response = new Response<>();
		response.setData(operacaoService.buscarTodos());
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(OperacaoController.class).listarOperacoes()).withSelfRel());
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		
		
	}
	
	@ApiOperation(value = "Retorna uma lista de contas paginadas")  
	@GetMapping(value = "/listarTodasPaginadas")
	public ResponseEntity<Response<Page<OperacaoDto>>> listarOperacoesPaginadas(@RequestParam(defaultValue = "0") int page,
				 				      										  @RequestParam(defaultValue = "5") int size){
		
		Pageable pageable = PageRequest.of(page, size);
		Response<Page<OperacaoDto>>   response = new Response<>();
		response.setData(operacaoService.listarTodas(pageable));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OperacaoController.class).listarOperacoesPaginadas(page, size)).withSelfRel());
		return  ResponseEntity.status(HttpStatus.OK).body(response);
				
	}
	
	
	
	
}
