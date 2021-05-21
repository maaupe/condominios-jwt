package br.com.condominio.app.controller;

import java.util.List;
import java.util.logging.Logger;

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

import br.com.condominio.app.config.SwaggerConfig;
import br.com.condominio.app.constant.HyperLinkConstant;
import br.com.condominio.app.controller.dto.CondominioDto;
import br.com.condominio.app.message.response.Response;
import br.com.condominio.app.modelo.service.ICondominioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = SwaggerConfig.CONDOMINIO)
@RequestMapping("/api/condominios")
@RestController
public class CondominioController {

	private static final Logger logger = Logger.getLogger(CondominioController.class.getName());

	@Autowired
	private ICondominioService condominioService;

	
	
	@PostMapping(value="/novo")
	public ResponseEntity<Response<Boolean>> criarCondominio(@RequestBody CondominioDto condominio) {
			
		System.out.println("************* cadastr condominio ****************");
		Response<Boolean> response = new Response<>();
		condominio.setStatus("Ativo");
		response.setData(this.condominioService.salvar(condominio));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CondominioController.class).criarCondominio(condominio))
				.withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CondominioController.class).listarCondominios())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	
	@ApiOperation(value = "Retorna uma lista de condominios")
	@ApiResponses(value = { 
			        @ApiResponse(code = 200,message = "Lista de Condominios Exibida com Sucesso"),
			        @ApiResponse(code = 500,message= "Erro interno no servico"),
			      })
	@GetMapping(value = "/listar-todos")
	public ResponseEntity<Response<List<CondominioDto>>> listarCondominios() {
		Response<List<CondominioDto>> response = new Response<>();
		response.setData(this.condominioService.buscaTodos());
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(CondominioController.class).listarCondominios()).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	
	}	

	@ApiOperation(value = "Retorna uma condominio pelo id")
	@GetMapping("/getById/{idCondominio}")
	public ResponseEntity<Response<CondominioDto>> listarCondominioPorId(@PathVariable Long idCondominio) {

		Response<CondominioDto> response = new Response<>();
		response.setData(this.condominioService.BuscaPorId(idCondominio) );
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(CondominioController.class).listarCondominioPorId(idCondominio)).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@ApiOperation(value = "Altera um condominio")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Condominio atualizado com sucesso"),
			@ApiResponse(code = 400, message = "Erro na requisição enviada pelo cliente"),
			@ApiResponse(code = 404, message = "Condominio não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno no serviço"),
	})
	
	@PutMapping(value="/editar")
	public ResponseEntity<Response<Boolean>> atualizarCondominio(@RequestBody CondominioDto condominio) {
        condominio.setStatus("1");			
		Response<Boolean> response = new Response<>();
		response.setData(this.condominioService.atualizar(condominio));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CondominioController.class).atualizarCondominio(condominio))
				.withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CondominioController.class).listarCondominios())
				.withRel(HyperLinkConstant.ATUALIZAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	
	@ApiOperation(value = "Deleta o condominio pelo id")
	@DeleteMapping("/delete/{idCondominio}")
	public ResponseEntity<Response<Boolean>> excluir(@PathVariable Long idCondominio) {
		Response<Boolean> response = new Response<>();
		response.setData(this.condominioService.deletar(idCondominio));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CondominioController.class).excluir(idCondominio))
				.withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CondominioController.class).listarCondominios())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
}
