package br.com.condominio.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.condominio.app.controller.dto.UnidadeDto;
import br.com.condominio.app.message.response.Response;
import br.com.condominio.app.modelo.service.IUnidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api ( value = "API Unidades" ) 
@RequestMapping("/api/unidades")
@RestController
public class UnidadeController {
	
	@Autowired
	private IUnidadeService unidadeService;
	
	@ApiOperation(value = "Retorna uma lista de unidades")  
	@GetMapping(value = "/listar-todas")
	public ResponseEntity<Response<List<UnidadeDto>>> listarUnidades(){
		
		Response<List<UnidadeDto>> response = new Response<>();
		response.setData(unidadeService.listarTodas());
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(UnidadeController.class).listarUnidades()).withSelfRel());
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@ApiOperation(value = "Cadastrar uma nova unidade")  
	@PostMapping(value="/nova")
	public ResponseEntity<Response<Boolean>> cadastrarUnidade(@Valid @RequestBody UnidadeDto unidade){
		
		Response<Boolean> response = new Response<>();
		response.setData(unidadeService.salvar(unidade));
		response.setStatusCode(HttpStatus.OK.value());
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	
	@GetMapping("/unidade/{idUnidade}")
	public ResponseEntity<Response<UnidadeDto>> consultarCursoPorCodigo(@PathVariable Long idUnidade) {
		Response<UnidadeDto> response = new Response<>();
		response.setData(this.unidadeService.buscaPorId(idUnidade));
		response.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@ApiOperation(value = "Atualizar uma nova unidade")  
	@PutMapping(value="/editar/{idUnidade}")
	public ResponseEntity<Response<Boolean>> atualizarCurso(@Valid @RequestBody UnidadeDto unidade) {
		Response<Boolean> response = new Response<>();
		response.setData(unidadeService.atualizar(unidade));
		response.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(value="/excluir/{idUnidade}")
	public ResponseEntity<Response<Boolean>> excluirUnidade(@PathVariable Long idUnidade) {
		
		Response<Boolean> response = new Response<>();
		
		response.setData(this.unidadeService.deletar(idUnidade));
		response.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	
	}

}
