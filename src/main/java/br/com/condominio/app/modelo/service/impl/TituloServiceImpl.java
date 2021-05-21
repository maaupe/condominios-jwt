package br.com.condominio.app.modelo.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import br.com.condominio.app.constant.MensagensConstant;
import br.com.condominio.app.controller.dto.CondominioDto;
import br.com.condominio.app.controller.dto.TituloDto;
import br.com.condominio.app.controller.dto.UnidadeDto;
import br.com.condominio.app.exception.TituloException;
import br.com.condominio.app.modelo.Banco;
import br.com.condominio.app.modelo.ItemTitulo;
import br.com.condominio.app.modelo.Operacao;
import br.com.condominio.app.modelo.StatusTitulo;
import br.com.condominio.app.modelo.Titulo;
import br.com.condominio.app.modelo.Unidade;
import br.com.condominio.app.modelo.filtro.BoletoFilter;
import br.com.condominio.app.modelo.filtro.RemessaFilter;
import br.com.condominio.app.modelo.filtro.TituloFilter;
import br.com.condominio.app.modelo.repository.ITituloRepository;
import br.com.condominio.app.modelo.service.ICondominioService;
import br.com.condominio.app.modelo.service.IOperacaoService;
import br.com.condominio.app.modelo.service.ITituloService;
import br.com.condominio.app.modelo.service.IUnidadeService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jrimum.bopepo.pdf.Files;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.ClassLoaders;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.exemplo.banco.bradesco.NossoNumero;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;


@Service
public class TituloServiceImpl implements ITituloService {

	
    private static final String TITULO_NAO_ENCONTRADO = " Erro  Titulo não encontrado !!!";
    private static final String MENSAGEM_ERRO = "Erro interno. Contate o Suporte";
	
    @Value("${pdf.path}")
    private String pdfFilesPath;
    
	@Autowired
	private ICondominioService condominioService;
		
	@Autowired
	private IUnidadeService unidadeService;

	@Autowired
	private IOperacaoService operacaoService;
	
	
	private ITituloRepository tituloRepository;
	private ModelMapper mapper;
	
	private BigDecimal valorCondominio;
	private BigDecimal valorCotaExtra;
	private BigDecimal valorGaragenExtra;
	private LocalDate dataVencimento;
	private Titulo recibo;
	private Banco banco; 
	
	//********************************* variaveis do remessa *************************
	// variaveis para criar o arquivo
	int convenio = 2866935 ;
	int sequencial ; // Variável usada para seta a sequencia dos arquivos
	String nDocumento = "000000000";	
	
	@Autowired
	public TituloServiceImpl(ITituloRepository tituloRepository) {
		this.tituloRepository = tituloRepository;
		this.mapper = new ModelMapper();
		this.banco =  new Banco( "237", "agencia", "c/c", "BRADESCO","00000000000000008911");
		
		
	}
	
	
	
	public List<Titulo> buscaPoridCondominioEvencimento(RemessaFilter filtro){
		
		List<Titulo> titulos = tituloRepository.findByUnidadeCondominioIdAndVencimento(filtro.getIdCondominio(), LocalDate.parse(filtro.getVencimento()) );
		
		return titulos;
	}
	
	

	@Override
	@Transactional
	public Titulo salvar(Titulo titulo) {
		// 
		System.out.println(titulo.getCreatedBy());
		return tituloRepository.save(titulo);
	}

	@Override
	public void deletarPorid(Long idTitulo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(Titulo titulo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TituloDto> buscaPorNomeContendo(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Titulo alterar(Titulo titulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TituloDto> buscaPorNomecomecandoPorIgnorandoMaisculo(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TituloDto BuscaPorId(Long idTitulo) {
		try {
			Optional<Titulo> tituloOptional = this.tituloRepository.findById(idTitulo);
			if(tituloOptional.isPresent()) {
				return this.mapper.map(tituloOptional.get(), TituloDto.class);
			}
			throw new TituloException(TITULO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
		} catch (TituloException c) {
			throw c;
		} catch (Exception e) {
			
			throw new TituloException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public Page<TituloDto> buscarTodos(Pageable pageable) {
		try {
			Page<Titulo> pageTitulos = this.tituloRepository.findAll(pageable) ;
            return this.mapper.map(pageTitulos, new TypeToken<Page<TituloDto>>(){}.getType());
		} catch (Exception e) {
			throw new TituloException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}			
			
	}
	
	@Override
	public List<TituloDto> buscarTodos() {
		
		try {
			
            return this.mapper.map(this.tituloRepository.findAll(), new TypeToken<List<TituloDto>>(){}.getType());			
		} catch (Exception e) {
			throw new TituloException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}			
			
	}
	
	@Override
	public Titulo baixarTitulo(int Numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gerarTitulo(TituloFilter filtro) {

		String isGerado = "";
		
		List<CondominioDto> condominios;

		if (filtro.getDiaVencimento() == 0) {

			System.out.println(filtro.getIdCondIni());
			System.out.println(filtro.getIdCondFinal());
			condominios = this.condominioService.findByFiltro(filtro.getIdCondIni(), filtro.getIdCondFinal());
            if (!condominios.isEmpty()) {
				if (preparaTitulo(condominios)) {
					isGerado =  "Recibo Gerado com sucesso!!!";
				}
            }else {
            	isGerado="Não ha condominios com este vencimento!!!";
            }
		} else if (filtro.getDiaVencimento() > 0) {
			condominios = this.condominioService.buscaDiaVenciento(filtro.getDiaVencimento());
		
	           if (!condominios.isEmpty()) {
					if (preparaTitulo(condominios)) {
						isGerado =  "Recibo Gerado com sucesso!!!";
					}
	            }else {
	            	
	            	isGerado="Não ha condominios com este vencimento!!!";
	            }

		}

		return isGerado;
		
	}

	public Boolean preparaTitulo(List<CondominioDto> condominios) {

		Boolean isGerado=false;
		
		for(CondominioDto condominio : condominios){
			
			int ano = LocalDate.now().getYear();
			int mes = LocalDate.now().getMonthValue();
			System.out.println("Diferenca entre o vencimento e a data autal" + (condominio.getDiaVencimento() - LocalDate.now().getDayOfMonth()) );
			
			if(condominio.getDiaVencimento() == 5 ) {
				mes ++;
			}

			this.dataVencimento = LocalDate.of(ano, mes, condominio.getDiaVencimento());

			this.valorCondominio = condominio.getValorCondominio();
			this.valorCotaExtra = condominio.getValorCotaExtra();
			this.valorGaragenExtra = condominio.getValorGaragemExtra(); 
			
			isGerado=gerarTituloCondominio(condominio.getId());
				

		};

		return isGerado;
	}

	public Boolean gerarTituloCondominio(Long idCondominio) {

		/*
		 * selecionar as unidades
		 */
		Boolean isGerado = false;
		
		List<UnidadeDto> unidades = unidadeService.buscaUnidadePorCondominio(idCondominio);
        
		if (unidades.isEmpty()) {
			System.out.println(MensagensConstant.ERRO_NAO_HA_UNIDADES.getValor());
		} else {
		
			for (UnidadeDto unidadeDto : unidades) {
				System.out.println("********* gerando recibos das unidades *********");
				
				BigDecimal total = BigDecimal.ZERO;
				
				Unidade unidade = new Unidade();
				
				Titulo titulo = new Titulo();
				
				unidade.setId(unidadeDto.getId());
				unidade.setNumero(unidadeDto.getNumero());
				titulo.setUnidade(unidade);
				titulo.setVencimento(this.dataVencimento);
				titulo.setStatus(StatusTitulo.GERADO);
								
				//criar um item do titulo 
				Operacao operacao = new Operacao();
				
				ItemTitulo item1 = new ItemTitulo();

				operacao = operacaoService.buscarPorId(100L);
				
				item1.setOperacao(operacao);
		
				item1.setReferencia(this.dataVencimento.getMonthValue() + "/" + this.dataVencimento.getYear());
					
				item1.setValor(this.valorCondominio);
				
				titulo.adicionaItemTitulo(item1);
			
                     				
				if (this.valorCotaExtra.compareTo(BigDecimal.ZERO) == 1) {
										
					ItemTitulo item = new ItemTitulo();
					Operacao operacao1 = new Operacao();  
					operacao1 = operacaoService.buscarPorId(101L);
					
					item.setOperacao(operacao1);
					item.setReferencia(this.dataVencimento.getMonthValue() + "/" + this.dataVencimento.getYear());
					item.setValor(this.valorCotaExtra);
										
					titulo.adicionaItemTitulo(item);
					
				}
				
				System.out.println("resultado do campo boolean --> "+ unidadeDto.getTemGaragem());
				
				if( unidadeDto.getTemGaragem()  ) {
					
					ItemTitulo itemg = new ItemTitulo();
					Operacao operacaog = new Operacao();  
					operacaog = operacaoService.buscarPorId(102L);
					
					itemg.setOperacao(operacaog);
					itemg.setReferencia(this.dataVencimento.getMonthValue() + "/" + this.dataVencimento.getYear());
					itemg.setValor(this.valorGaragenExtra);
										
					titulo.adicionaItemTitulo(itemg);

				}

				titulo.setValor(titulo.getTotalTitulo());
				this.salvar(titulo);
				
				isGerado=true;
			}

		}
		return isGerado;

	}



	@Override
	public File gerarBoletoPdf(BoletoFilter filtro) throws IOException {
		
		String isGerado = "";
		List<Titulo> titulos = new ArrayList<Titulo>() ;
		File file = null;
		if(filtro.getVencimento()== null && filtro.getIdCondominio()==0 && filtro.getIdUnidade()==0 ) {
			isGerado =  "o Filtro para Emissao nao pode ser vazio !!!";
		}else {
		   if(filtro.getIdCondominio() > 0 && filtro.getIdUnidade() > 0 && (filtro.getVencimento().isEmpty())) {
			   titulos =  this.tituloRepository.findByUnidadeIdAndUnidadeCondominioId(filtro.getIdUnidade(), filtro.getIdCondominio());
			   if (titulos.size() > 0) {
				   isGerado =  "ha dados no filtro para geração dos boletos!!!";			
			   }else {					
		    	   isGerado =  "Não ha titulos com esta caracteristica!!!";				
			   }
		   }else if (filtro.getIdCondominio()>0 && filtro.getIdUnidade()>0 && !filtro.getVencimento().isEmpty() ) {
						   
			   LocalDate dataVencimento = LocalDate.parse(filtro.getVencimento());	
			   
			   Titulo titulo =  this.tituloRepository.findByUnidadeIdAndUnidadeCondominioIdAndVencimento(filtro.getIdUnidade(), filtro.getIdCondominio(), dataVencimento);
			   if (!titulos.isEmpty() || titulos != null) {
				   //retornou um titulo entao gerar boleto destetitulo
				   file = preencheBoleto(titulo);
				   return file;
				   //isGerado =  "Boleto gerado com Sucesso!!!";			
			   }else {					
		    	   isGerado =  "Não ha titulos com esta caracteristica!!!";				
			   }
		   }
		}
		

		
    		
		/*if (filtro.getDiaVencimento() == 0) {

			System.out.println(filtro.getIdCondominio());
			System.out.println(filtro.getIdUnidade());
			
			titulos = this.tituloRepository.findByFiltro(filtro.getIdCondominio(), filtro.getIdUnidade());
			
            if (!condominios.isEmpty()) {
				if (preparaTitulo(condominios)) {
					isGerado =  "Boleto Gerado com sucesso!!!";
				}
            }else {
            	isGerado="Não ha condominios titulos com este vencimento!!!";
            }
		} else if (filtro.getDiaVencimento() > 0) {
			condominios = this.condominioService.buscaDiaVenciento(filtro.getDiaVencimento());
		
	           if (!condominios.isEmpty()) {
					if (preparaTitulo(condominios)) {
						isGerado =  "Recibo Gerado com sucesso!!!";
					}
	            }else {
	            	
	            	isGerado="Não ha condominios com este vencimento!!!";
	            }

		}*/

		return file;
	}



  public File preencheBoleto(br.com.condominio.app.modelo.Titulo recibo) throws IOException{
	  
	  
	  this.recibo = recibo; 
		/*
       * INFORMANDO DADOS SOBRE O CEDENTE.
       */
      //Cedente cedente = new Cedente("Condominio do Edificio Joao Paulo II", "08.946.179/0001-86");
	  Cedente cedente = new Cedente(recibo.getUnidade().getCondominio().getRazaoSocial(),recibo.getUnidade().getCondominio().getCnpj());
      
      /*
       * INFORMANDO DADOS SOBRE O SACADO.
       */
      Sacado sacado = new Sacado(recibo.getUnidade().getResponsavel().getNome(), recibo.getUnidade().getResponsavel().getCpf_cnpj());

      // Informando o endereço do sacado.
      Endereco enderecoSac = new Endereco();
      enderecoSac.setUF( UnidadeFederativa.RJ);
      enderecoSac.setLocalidade(recibo.getUnidade().getCondominio().getEndereco().getCidade());
      enderecoSac.setCep(new CEP(recibo.getUnidade().getCondominio().getEndereco().getCep()));
      enderecoSac.setBairro(recibo.getUnidade().getCondominio().getEndereco().getBairro());
      enderecoSac.setLogradouro(recibo.getUnidade().getCondominio().getEndereco().getLogradouro());
      enderecoSac.setNumero(recibo.getUnidade().getCondominio().getEndereco().getNumero() +" "+recibo.getUnidade().getTipounidade()+" "+recibo.getUnidade().getNumero());
      sacado.addEndereco(enderecoSac);

      /*
       * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
       
      SacadorAvalista sacadorAvalista = new SacadorAvalista("JRimum Enterprise", "00.000.000/0001-91");

      // Informando o endereço do sacador avalista.
      Endereco enderecoSacAval = new Endereco();
      enderecoSacAval.setUF(UnidadeFederativa.DF);
      enderecoSacAval.setLocalidade("Brasília");
      enderecoSacAval.setCep(new CEP("59000-000"));
      enderecoSacAval.setBairro("Grande Centro");
      enderecoSacAval.setLogradouro("Rua Eternamente Principal");
      enderecoSacAval.setNumero("001");
      sacadorAvalista.addEndereco(enderecoSacAval);*/

      /*
       * INFORMANDO OS DADOS SOBRE O TÍTULO.
       */
      
      // Informando dados sobre a conta bancária do título.
      ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
      contaBancaria.setNumeroDaConta(new NumeroDaConta(54302, "0"));
      contaBancaria.setCarteira(new Carteira(9));
      contaBancaria.setAgencia(new Agencia(1698, "5"));
      // com sacador avalist
      //Titulo titulo = new Titulo(contaBancaria, sacado, cedente, sacadorAvalista);
      // sem sacador avalista
      org.jrimum.domkee.financeiro.banco.febraban.Titulo titulo = new org.jrimum.domkee.financeiro.banco.febraban.Titulo(contaBancaria, sacado, cedente);
      
      titulo.setNumeroDoDocumento(recibo.getUnidade().getNumero()+"/"+ String.format("%02d",  recibo.getVencimento().getMonthValue()) +"/"+ String.valueOf(recibo.getVencimento().getYear()).substring(2, 4) );
                  
      String[] vnossoNumero = String.valueOf(NossoNumero.valueOf(Long.parseLong( recibo.getNossoNumero()), (int) contaBancaria.getCarteira().getCodigo( ))).split("-") ;
       
      
      titulo.setNossoNumero(vnossoNumero[0]);
      titulo.setDigitoDoNossoNumero(vnossoNumero[1]);
      
      titulo.setValor(BigDecimal.valueOf(0.23));
      titulo.setDataDoDocumento(new Date());
      titulo.setDataDoVencimento(java.sql.Date.valueOf(recibo.getVencimento()) );
      titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
      titulo.setAceite(Aceite.N);
      titulo.setDesconto(BigDecimal.ZERO);
      titulo.setDeducao(BigDecimal.ZERO);
      titulo.setMora(BigDecimal.ZERO);
      titulo.setAcrecimo(BigDecimal.ZERO);
      titulo.setValorCobrado(BigDecimal.ZERO);
      titulo.setValor(recibo.getValor());
      /*
       * INFORMANDO OS DADOS SOBRE O BOLETO.
       */
      Boleto boleto = new Boleto(titulo);
      
      boleto.setLocalPagamento("Pagável preferencialmente na Rede Bradesco e Bradesco Expresso," +
                      "qualquer Banco até o Vencimento.");
      boleto.setInstrucaoAoSacado("");
      boleto.setInstrucao1("*** VALORES EXPRESSOS EM REAIS ***");
      boleto.setInstrucao2("");
      boleto.setInstrucao3("MORA DIA/COM. PERMANENCIA........................." + valorMora(recibo.getValor()));
      boleto.setInstrucao4("APÓS "+ recibo.getVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " MULTA............................." + valorMulta(recibo.getValor()));
      boleto.setInstrucao5("");
      boleto.setInstrucao6("");
      boleto.setInstrucao7("");
      boleto.setInstrucao8("");

      /*
       * GERANDO O BOLETO BANCÁRIO.
       */
      // Instanciando um objeto "BoletoViewer", classe responsável pela
      // geração do boleto bancário.
      //BoletoViewer boletoViewer = new BoletoViewer(boleto);
      
      
      // Gerando o arquivo. No caso o arquivo mencionado será salvo na mesma
      // pasta do projeto. Outros exemplos:
      // WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
      // LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
       
       //Informando o template personalizado:
      //System.out.println("PATH... -> " + new File(".").getAbsolutePath()); raiz do sistema
        
       File templatePersonalizado = new File(ClassLoaders.getResource("./templates/BoletoTemplatePersonalizadoSimples.pdf").getFile());
       
       System.out.println(templatePersonalizado.getAbsolutePath());
       BoletoViewer boletoViewer = new BoletoViewer(boleto, templatePersonalizado);

       String mes = String.format("%02d",recibo.getVencimento().getMonthValue()); 
       String dia = String.format("%02d",recibo.getVencimento().getDayOfMonth());
       String nomeFile = mes + dia + String.valueOf(recibo.getUnidade().getNumero());
    		   
       //String   nomeFile = String.valueOf(recibo.getVencimento().getMonthValue() ) +String.valueOf(recibo.getVencimento().getDayOfMonth())+
       //String.valueOf(recibo.getUnidade().getNumero());
		String caminhoPasta;
		
		//Pegando o caminho raiz
		caminhoPasta = new File(".").getCanonicalPath();
		
		//concatenando o caminho raiz com o nome da pasta onde estão nossos arquivos
		caminhoPasta = caminhoPasta.concat("\\").concat(nomeFile+".pdf");
		
		System.out.println(caminhoPasta);
       
       File arquivoPdf = boletoViewer.getPdfAsFile(caminhoPasta);
       
       //Path path = Paths.get(arquivoPdf.getAbsolutePath());
       //ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
       
       return arquivoPdf;
       //mostreBoletoNaTela(arquivoPdf);
	  
  }



private BigDecimal valorMulta(BigDecimal valor) {
	
	//colocar uma variavel no properties
	double percentagem = 2.00;
	
	BigDecimal valordaMulta = valor.multiply(BigDecimal.valueOf(percentagem/100));
	
	return valordaMulta.setScale(2, RoundingMode.HALF_EVEN);
}



private BigDecimal valorMora(BigDecimal valor) {
	
	double percentagem = 0.334 ;
	
	BigDecimal valordaMora = valor.multiply(BigDecimal.valueOf(percentagem/100));
	
	return valordaMora.setScale(2, RoundingMode.HALF_EVEN);
}



@Override
public String gerarRemessa(RemessaFilter filtro) throws IOException {
	
	
	//pegar dados para geracao do arquivo baseado no filtro
	if(filtro!=null) {
		List<Titulo> recibos = buscaPoridCondominioEvencimento(filtro);	
	}
	
	System.out.println("pegou os recibos com este vencimento do condominio");
	
	
	
	//---- variave3is para criar o arquivo
	
	File layoutTemporario = new File("LayoutRemessa_BBCNAB400.txg.xml");
	File layout = Files.bytesToFile(layoutTemporario, IOUtils.toByteArray(ClassLoaders.getResourceAsStream("/layoutBanco/LayoutBradescoCNAB400Envio.txg.xml")));

	// Criando o arquivo de remessa
	FlatFile<Record> ff = Texgit.createFlatFile(layout);
	
	// Registrando Header
	ff.addRecord(createHeader(ff));
	
	
	/* Laço para representar mais de 1 boleto registrado no arquivo
	*  Registrando Títulos(boletos)
	**/
	for( sequencial = 1; sequencial <= 5; sequencial++){ 
		ff.addRecord(createTransacaoTitulos(ff, sequencial));
	}
	
	// Registrando Trailer
	ff.addRecord(createTrailer(ff, sequencial));

	// Salvando arquivo de remessa.
	File arquivoDeRemessa = new File("resource/arquivoRemessa/CB"+new SimpleDateFormat("ddMM").format(new Date())+"01"+".TST");
	FileUtils.writeLines(arquivoDeRemessa, ff.write(), "\r\n");
	System.out.println("Arquivo gerado com sucesso em: "+ arquivoDeRemessa);

	if (layoutTemporario != null) {
		layoutTemporario.delete();
	}
	return " ";
}



private Record createTrailer(FlatFile<Record> ff, int sequencial) {
	Record trailer = ff.createRecord("Trailler");
	
	trailer.setValue("NumeroSequencialRegistro", sequencial);
	
	return trailer;
}



private Record createTransacaoTitulos(FlatFile<Record> ff, int sequencial) {
	/*
	 * NOTA : as linhas comentadas são setadas por padrão no layout do arquivo
	 */
	Record transacaoTitulos = ff.createRecord("TransacaoTitulo");

	/*
	transacaoTitulos.setValue("IdentiFicacaoRegistroDetalhe", 7);
	transacaoTitulos.setValue("TipoInscricaoCedente", 02);
	*/
	transacaoTitulos.setValue("NumeroCpfCnpjCedente", "06313870000441");
	transacaoTitulos.setValue("PrefixoAgencia", 1623);
	transacaoTitulos.setValue("DigitoVerificadorAgencia", 2);
	transacaoTitulos.setValue("NumeroContaCorrenteCedente", 101916);
	transacaoTitulos.setValue("DigitoVerificadorConta", 0);
	transacaoTitulos.setValue("NumeroCovenioCobrancaCedente", convenio);
	//transacaoTitulos.setValue("CodigoControleEmpresa", "");
	transacaoTitulos.setValue("NossoNumero", convenio+nDocumento+sequencial);
	/*
	transacaoTitulos.setValue("NumeroPrestacao", 00);
	transacaoTitulos.setValue("GrupoValor", 00);
	transacaoTitulos.setValue("IndicativoMensagemSacador", "");
	transacaoTitulos.setValue("PrefixoTitulo", "   ");
	*/
	transacaoTitulos.setValue("VariacaoCarteira", "019");
	/*
	transacaoTitulos.setValue("ContaCaucao", 0);
	transacaoTitulos.setValue("NumeroBordero", 000000);
	transacaoTitulos.setValue("TipoCobranca", "     ");
	*/
	transacaoTitulos.setValue("CarteiraCobranca", 17);
	transacaoTitulos.setValue("Comando", 01);
	//transacaoTitulos.setValue("NumeroTituloAtribuidoCedente", "          ");
	transacaoTitulos.setValue("DataVencimento", "190118");
	transacaoTitulos.setValue("ValorTitulo", 1);
	/*transacaoTitulos.setValue("NumeroBanco", 001);
	transacaoTitulos.setValue("PrefixoAgenciaCobradora", 0000);
	transacaoTitulos.setValue("DigitoVerificadorPrefixoAgenciaCobradora", " ");
	*/
	transacaoTitulos.setValue("EspecieTitulo", 12);
	transacaoTitulos.setValue("AceiteTitulo", "N");
	transacaoTitulos.setValue("DataEmissao", 170118);
	/*
	transacaoTitulos.setValue("InstrucaoCodificada", "  ");
	transacaoTitulos.setValue("InstrucaoCodificada", "  ");
	*/
	transacaoTitulos.setValue("JurosMoraDiaAtraso", "00000000003");
	/*transacaoTitulos.setValue("DataLimite", "      ");
		transacaoTitulos.setValue("ValorDesconto", "           "); 
	transacaoTitulos.setValue("ValorIof", "           ");
	transacaoTitulos.setValue("ValorAbatimento", "           ");
	*/
	transacaoTitulos.setValue("TipoInscricaoSacado", 01);
	transacaoTitulos.setValue("NumeroCnpjCpfSacado", "12345678900");
	transacaoTitulos.setValue("NomeSacado", "FULADO DA SILVA SAURO");
	//transacaoTitulos.setValue("ComplementoRegistro", "");
	transacaoTitulos.setValue("EnderecoSacado", "Rua Augusto Corrêa, LABES");
	transacaoTitulos.setValue("BairroSacado", "Guamá");
	transacaoTitulos.setValue("CepEnderecoSacado", "66075110");
	transacaoTitulos.setValue("CidadeSacado", "BELÉM");
	transacaoTitulos.setValue("UfCidadeSacado", "PA");
	/*
	transacaoTitulos.setValue("Observacao", "                                        ");
	transacaoTitulos.setValue("NumeroDiasProtesto", "  ");
	transacaoTitulos.setValue("ComplementoRegistro", " ");
	*/
	transacaoTitulos.setValue("SequencialRegistro", sequencial);
	
	return transacaoTitulos;
}



private Record createHeader(FlatFile<Record> ff) {
	/*
	 * NOTA : as linhas comentadas são setadas por padrão no layout do arquivo
	 */
	ZoneId defaultZoneId = ZoneId.systemDefault();
	 LocalDate localDate = LocalDate.now();
	
	
	Record header = ff.createRecord("Header");
	 
	header.setValue("IdentificacaoRemessa", 0);
	//header.setValue("TipoOperacao", 1);
	header.setValue("LiteralRemessa", "REMESSA");
	header.setValue("CodigoDeServico", 1);
	header.setValue("LiteralServico", "COBRANCA");
	header.setValue("CodigoDaEmpresa", this.banco.getCodigodaEmpresa());
	header.setValue("NomeDaEmpresa", "Condominio BLABLA" );
	header.setValue("CodigoCompensacao", this.banco.getNumero());
	header.setValue("NomeBanco", this.banco.getNumero()+this.banco.getNome());
	header.setValue("DataGravacaoArquivo", Date.from(  localDate.atStartOfDay(defaultZoneId).toInstant()) );
	header.setValue("NumeroSequencialRegistro", 1);
	/*
	header.setValue("PrefixoAgencia", 1623);
	header.setValue("DigitoVerificadorAgencia", 2);
	header.setValue("NumeroContaCorrente", 101916);
	header.setValue("DigitoVerificadorContaCorrente", 0);
	header.setValue("NomeCedente", "FADESP");
	header.setValue("BB", "237BRADESCO");
	header.setValue("DataGravacao", 170118);
	header.setValue("SequencialRemessa", "0000000");
	header.setValue("NumeroConvenioLider", convenio);
	header.setValue("SequencialRegistro", 1);
	 */
	
	return header;
}
















}
