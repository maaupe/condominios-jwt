package br.com.condominio.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.condominio.app.modelo.Condominio;
import br.com.condominio.app.modelo.Endereco;
import br.com.condominio.app.modelo.service.impl.EnderecoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api ( value = "API Descricao" ) 
@RequestMapping("/api/endereco")
@RestController
public class EnderecoController {

	@Autowired
	private EnderecoServiceImpl enderecoService;
	
	@ApiOperation(value = "Altera um endereco especifico")  
	@PutMapping(value = "/atualizar/{id}")
	public Endereco update(@PathVariable Long id, @RequestBody Endereco endereco) {
		Endereco currentEndereco = this.enderecoService.BuscaPorId(id);
		return enderecoService.alterar(endereco);
	}
	
}
