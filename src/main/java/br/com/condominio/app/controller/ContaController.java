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

import br.com.condominio.app.controller.dto.ContaDto;
import br.com.condominio.app.message.response.Response;
import br.com.condominio.app.modelo.service.IContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api ( value = "API Responsaveis" ) 
@RequestMapping("/api/planocontas")
@RestController
public class ContaController {

	@Autowired
	private IContaService contaService;
	
	@ApiOperation(value = "Retorna uma lista de contas")  
	@GetMapping(value = "/listar-todas")
	public ResponseEntity<Response<List<ContaDto>>> listarContas(){
		Response<List<ContaDto>> response = new Response<>();
		response.setData(contaService.listarTodas());
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(ContaController.class).listarContas()).withSelfRel());
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@ApiOperation(value = "Retorna uma lista de contas paginadas")  
	@GetMapping(value = "/listarTodasPaginadas")
	public ResponseEntity<Response<Page<ContaDto>>> listarContasPaginadas(@RequestParam(defaultValue = "0") int page,
				 														  @RequestParam(defaultValue = "5") int size){
		
		Pageable pageable = PageRequest.of(page, size);
		Response<Page<ContaDto>>   response = new Response<>();
		response.setData(contaService.listarTodas(pageable));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(ContaController.class).listarContas()).withSelfRel());
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	
	
	
	
}