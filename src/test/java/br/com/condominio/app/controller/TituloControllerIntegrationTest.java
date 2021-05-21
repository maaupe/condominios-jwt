package br.com.condominio.app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import br.com.condominio.app.message.response.Response;
import br.com.condominio.app.modelo.Condominio;
import br.com.condominio.app.modelo.Endereco;
import br.com.condominio.app.modelo.TipoUnidade;
import br.com.condominio.app.modelo.Unidade;
import br.com.condominio.app.modelo.repository.ICondominio;
import br.com.condominio.app.modelo.repository.ITituloRepository;
import br.com.condominio.app.modelo.repository.IUnidadeRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(JUnitPlatform.class)
public class TituloControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
		
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private ICondominio condominioService;
	
	@Autowired
	private ITituloRepository tituloRepository;
	
	@Autowired
	IUnidadeRepository unidadeRepsoitory;
	
	
	@BeforeEach
	public void init() {
		this.montaBasedeDados();
	}
	

	@AfterEach
	public void finish() {
		
	}
	
	
	private String montaUri(String urn) {
		
		return "http://localhost"+this.port+"/api/condominios/listar"+urn;
		
	}
	

	private void montaBasedeDados() {
	
		Condominio cond1 = new Condominio();
		Endereco end1 = new Endereco();
		end1.setBairro("Centro");
		end1.setCep("21235500");
		end1.setCidade("Rio de janeiro");
		end1.setNumero("216");
		end1.setLogradouro("Rua sem saida");
		end1.setUf("RJ");
		
		Condominio cond2 = new Condominio();
		Endereco end2 = new Endereco();
		cond1.setCnpj("48319213000107");
		cond1.setDiaVencimento(30);
		cond1.setSindico("Soraior Hyndu");
		cond1.setRazaoSocial("Condominio Ruanpodu");
		cond1.setStatus("Ativo");
		cond1.setEndereco(end1);
		cond1.setValorCondominio(new BigDecimal("450.00"));
		
		cond2.setCnpj("14014814000190");
		cond2.setDiaVencimento(5);
		cond2.setSindico("Hagamenon Pedreira");
		cond2.setRazaoSocial("Condominio Mansoes");
		cond2.setStatus("Ativo");
		cond2.setEndereco(end1);
		cond2.setValorCondominio(new BigDecimal("550.00"));
				
		this.condominioService.saveAll(Arrays.asList(cond1,cond2));
		
		/*
		 * Unidades
		 */
		
		Unidade unidade1 = new Unidade();
		Unidade unidade2 = new Unidade();
		
		unidade1.setCondominio(cond1);
		unidade1.setGaragem(false);
		unidade1.setNumero("101");
		unidade1.setTipounidade(TipoUnidade.APTO);
		
		unidade2.setCondominio(cond1);
		unidade2.setGaragem(false);
		unidade2.setNumero("301");
		unidade2.setTipounidade(TipoUnidade.APTO);
		
		unidadeRepsoitory.saveAll(Arrays.asList(unidade1,unidade2));
		
	}
	
   @Test	
   private void testListarCondominios() {
	   
	   ResponseEntity<Response<List<Condominio>>> condominios = 
	   restTemplate.exchange(this.montaUri(""),HttpMethod.GET, null, new ParameterizedTypeReference<Response<List<Condominio>>>(){ });  
	   
	   assertNotNull(condominios.getBody().getData());
	   assertEquals(2,condominios.getBody().getData().size());
	   assertEquals(200,condominios.getBody().getStatusCode());
	   
		//this.condominioService.findAll().forEach(condominio -> {
		//    System.out.println(condominio.toString());});

	   
   }
	
}
