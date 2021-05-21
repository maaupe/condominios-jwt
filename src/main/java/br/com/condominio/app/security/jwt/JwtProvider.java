package br.com.condominio.app.security.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.condominio.app.modelo.service.impl.UsuarioCustom;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${security.jwt.expiracao}")
    private int expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String jwtSecret;
    
    private static final String AUTHORITIES_KEY = "roles"; 
    
    public String generateJwtToken(Authentication authentication ) throws JsonProcessingException{
    	
    	UsuarioCustom usuarioPrincipal = (UsuarioCustom) authentication.getPrincipal();
    	
    	final String authorities = authentication.getAuthorities().stream()
    			                   .map(GrantedAuthority::getAuthority)
    	     	                   .collect(Collectors.joining(","));
    	  

        return Jwts
                .builder()
                .setSubject(usuarioPrincipal.getUsername())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiracao * 1000 ))
                .signWith(SignatureAlgorithm.HS512, jwtSecret )
                .compact();
    }

    private Claims obterClaims( String token ) throws ExpiredJwtException {
        return Jwts
                 .parser()
                 .setSigningKey(jwtSecret)
                 .parseClaimsJws(token)
                 .getBody();
    }

    public boolean tokenValido( String token ){
        try{
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data =
                    dataExpiracao.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }
    
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}
