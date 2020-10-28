package br.com.condominio.app.config;

import java.util.Arrays;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.condominio.app.auth.filter.JWTAuthenticationFilter;
import br.com.condominio.app.auth.filter.JWTAuthorizationFilter;
import br.com.condominio.app.auth.service.JWTService;
import br.com.condominio.app.modelo.service.impl.JpaUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;

	  private static final String[] AUTH_WHITELIST = {
	            "/login"
	    };
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        {
            http.cors().and().csrf().disable().authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .antMatchers(HttpMethod.POST, "/api/usario/signup").permitAll()
                    .anyRequest().authenticated()
                    .and()
                	.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
            		.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

//    	http.cors().and().csrf().disable().authorizeRequests()
//    	.antMatchers(AUTH_WHITELIST).permitAll().and()
//    	    .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//	        .and().authorizeRequests().antMatchers("/").permitAll().and()
//	        .authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
//            .antMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
//			.anyRequest().authenticated().and()
//			.addFilter(new JWTAuthenticationFilter( authenticationManager(), jwtService))
//			.addFilter(new JWTAuthorizationFilter( authenticationManager(), jwtService))
//			.csrf().disable().exceptionHandling().and()
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
    
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}

	
	 @Bean
	  public CorsConfigurationSource corsConfigurationSource(){
	    
		 final CorsConfiguration corsConfiguration = new CorsConfiguration();
	 
	    corsConfiguration.setAllowCredentials(true);
	    corsConfiguration
	        .setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	    corsConfiguration.setAllowedMethods(Arrays.asList(CorsConfiguration.ALL));
	    corsConfiguration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
	 
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfiguration);

	    return source;
	  }
    
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", 
//        		                                     "Content-Length","Host"));
//        
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
    
    
    
    
    

}
