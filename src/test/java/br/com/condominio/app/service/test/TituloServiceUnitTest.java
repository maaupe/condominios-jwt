package br.com.condominio.app.service.test;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.condominio.app.modelo.Condominio;
import br.com.condominio.app.modelo.Endereco;
import br.com.condominio.app.modelo.TipoUnidade;
import br.com.condominio.app.modelo.Unidade;
import br.com.condominio.app.modelo.repository.ICondominio;
import br.com.condominio.app.modelo.repository.ITituloRepository;
import br.com.condominio.app.modelo.repository.IUnidadeRepository;
import br.com.condominio.app.modelo.service.ITituloService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TituloServiceUnitTest {

	@Mock
	private ICondominio condominioService;
	
	@Mock
	private ITituloRepository tituloRepository;
	
	@Mock
	private IUnidadeRepository unidadeRepsoitory; 
	
	
	@InjectMocks
	private ITituloService tituloService;
	
	
	@BeforeAll
	public static void init() {
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
				
		//condominioService.saveAll(Arrays.asList(cond1,cond2));
		
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
		
		//unidadeRepsoitory.saveAll(Arrays.asList(unidade1,unidade2));
		
		
		
	}
	
	
	
}
