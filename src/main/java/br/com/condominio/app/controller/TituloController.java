package br.com.condominio.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.condominio.app.controller.dto.TituloDto;
import br.com.condominio.app.message.response.Response;
import br.com.condominio.app.modelo.filtro.BoletoFilter;
import br.com.condominio.app.modelo.filtro.RemessaFilter;
import br.com.condominio.app.modelo.filtro.TituloFilter;
import br.com.condominio.app.modelo.service.ITituloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api ( value = "API Titulo" ) 
@RequestMapping("/api/titulos")
@RestController
public class TituloController {
	
	@Value("${pdf.path}")
    private String pdfFilesPath;
	
	@Autowired 
	private ITituloService tituloService;
	
	
	/*@ApiOperation(value = "Lista todos os titulos")
	@GetMapping("/listar-todos")
	public ResponseEntity<?> listarTodos(Pageable pageable) {
		
		//Response<String> response = new Response<>();
		//response.setData(tituloService.buscarTodos(pageable));
		//response.setStatusCode(HttpStatus.OK.value());
		//response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TituloController.class).listar-todos(pageable)).withSelfRel());
		
		//return  ResponseEntity.status(HttpStatus.OK).body(response);
		return  new ResponseEntity<>(tituloService.buscarTodos(pageable), HttpStatus.OK);
	}*/
	
	/*@ApiOperation(value = "Lista todos os titulos")
	@GetMapping("/listar-todos")
	public ResponseEntity<Response<Page<TituloDto>>> listarTodos(@RequestParam(defaultValue = "0") int page,
		      													@RequestParam(defaultValue = "3") int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		Response<Page<TituloDto>> response = new Response<>();
		response.setData( this.tituloService.buscarTodos(pageable) );
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TituloController.class).listarTodos(page,size)).withSelfRel());
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		//return  new ResponseEntity<>(tituloService.buscarTodos(pageable), HttpStatus.OK);
	}*/
	
	@ApiOperation(value = "Lista todos os titulos")
	@GetMapping("/listar-todos")
	public ResponseEntity<Response<List<TituloDto>>> listarTodos() {
				
		Response<List<TituloDto>> response = new Response<>();
		System.out.println(this.tituloService.buscarTodos().toString());
		response.setData( this.tituloService.buscarTodos() );
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TituloController.class).listarTodos()).withSelfRel());
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		//return  new ResponseEntity<>(tituloService.buscarTodos(pageable), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Lista todos os titulos")
	@GetMapping("/listarTitulosPaginados")
	public ResponseEntity<Response<Page<TituloDto>>> listarTodos(@RequestParam(defaultValue = "0") int page,
		      													 @RequestParam(defaultValue = "5") int size) {
        System.out.println("Numero da pagina  :" + page);				
        System.out.println("Quantidade de registros : " + size);
        
		Pageable pageable = PageRequest.of(page, size);
		Response<Page<TituloDto>> response = new Response<>();
		response.setData( this.tituloService.buscarTodos(pageable) );
		System.out.println("voltou ao controller ****************");
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TituloController.class).listarTodos(page,size)).withSelfRel());
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		//return  new ResponseEntity<>(tituloService.buscarTodos(pageable), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Gera titulos de condominio do vencimento / condominio")
	@PostMapping("/gerar")
	public ResponseEntity<Response<?>> gerar(@RequestBody TituloFilter filtro) {
		Response<String> response = new Response<>();
		response.setData((tituloService.gerarTitulo(filtro)));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TituloController.class).gerar(filtro)).withSelfRel());
		System.out.println(response.toString());
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@ApiOperation(value = "Lista Titulos por id ")
	@GetMapping("/tituloPorId/{id}")
	public ResponseEntity<Response<TituloDto>> listarPorId(@PathVariable Long id) {
		
		Response<TituloDto> response = new Response<>();
		
		response.setData(this.tituloService.BuscaPorId(id) );
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TituloController.class).listarPorId(id)).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	/*@ApiOperation(value = "Listar Boletos bancarios")
	@PostMapping("/boletosCnab")
	public ResponseEntity<Response<Resource>> gerarBoletoPdf(@RequestBody BoletoFilter filtro){

		System.out.println("Entrou no controller");
		
		Response<String> response = new Response<>();
		response.setData((tituloService.gerarBoletoPdf(filtro)));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TituloController.class).gerarBoletoPdf(filtro)).withSelfRel());
		
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		
	}*/
	
	@ApiOperation(value = "Listar Boletos bancarios")
	@PostMapping("/boletosCnab")
	public ResponseEntity<Resource> gerarBoletoPdf(@RequestBody BoletoFilter filtro) throws Exception {
		System.out.println("*******************  gera o boleto em pdf *****************");
		System.out.println(filtro.toString());
		
		File file =  tituloService.gerarBoletoPdf(filtro);
		HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename="+file.getName());
      
    	Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		
		return  ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
		
	}
	
	@GetMapping("/recibo/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
		
		String caminhoPasta = new File(".").getCanonicalPath();
		caminhoPasta = caminhoPasta.concat("\\");
		
        Resource resource = new UrlResource(Paths.get(caminhoPasta)
                                                 .resolve(filename)
                                                 .toUri());
        System.out.println(resource.toString());
        System.out.println(resource.exists());
        System.out.println(resource.isReadable());
        System.out.println("***************************************************");
           
        if (resource.exists() || resource.isReadable()) {
            String contentDisposition = String.format("inline; filename=\"%s\"", resource.getFile()
                                                                                         .getName());
            return ResponseEntity.ok()
                                 .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                                 .body(resource);
        }

        return ResponseEntity.notFound()
                             .build();
    }
	
	@ApiOperation(value = "Gerar arquivo remessa para banco")
	@PostMapping("/gerarRemessa")
	public ResponseEntity<Response<?>> remessa(@RequestBody RemessaFilter filtro) throws IOException {
		Response<String> response = new Response<>();
		try {
			response.setData(tituloService.gerarRemessa(filtro));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TituloController.class).remessa(filtro)).withSelfRel());
		System.out.println(response.toString());
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	
	
	
	
}
