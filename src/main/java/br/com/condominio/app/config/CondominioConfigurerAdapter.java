package br.com.condominio.app.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CondominioConfigurerAdapter implements WebMvcConfigurer{

	/*static final Pageable DEFAULT_PAGE_REQUEST = PageRequest.of(0, 5);

	
	@Override           
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		
		PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
		phmar.setFallbackPageable( DEFAULT_PAGE_REQUEST);
		argumentResolvers.add(phmar);
		
		
	}*/
}
