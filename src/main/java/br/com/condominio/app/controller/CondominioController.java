package br.com.condominio.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.condominio.app.modelo.Condominio;
import br.com.condominio.app.modelo.service.ICondominioService;



@RequestMapping("/api")
@RestController
public class CondominioController {
	
		@Autowired
		private ICondominioService condominioService;

		
		@GetMapping(value = "/condominio/listar-todos")
		@PreAuthorize("hasRole('USER')")
		public @ResponseBody List<Condominio> listarRest() {
			List<Condominio> condominios = condominioService.buscaTodos();
			return condominios;
		}
		
		@GetMapping("/condominio/getById/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public Condominio show(@PathVariable Long id) {
			return this.condominioService.BuscaPorId(id);
		}
		
		
		@PostMapping("/condominio/novo")
		@ResponseStatus(HttpStatus.CREATED)
		public Condominio create(@RequestBody Condominio condominio) {
			condominio.setDataCadastro(LocalDate.now());
			System.out.println(condominio.toString());
			this.condominioService.salvar(condominio);
			return condominio; 
		}
		
		@PutMapping("/condominio/update/{id}")
		@ResponseStatus(HttpStatus.CREATED)
		public Condominio update(@PathVariable Long id, @RequestBody Condominio condominio) {
			Condominio currentcondominio = this.condominioService.BuscaPorId(id);
			currentcondominio.setCnpj(condominio.getCnpj());
			currentcondominio.setRazaoSocial(condominio.getRazaoSocial());
			currentcondominio.setSindico(condominio.getSindico());
			this.condominioService.salvar(currentcondominio);
			return currentcondominio;
		}

		@DeleteMapping("/condominio/delete/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable Long id) {
			Condominio currentcondominio = this.condominioService.BuscaPorId(id);
			this.condominioService.deletar(currentcondominio);
		}
	
}
