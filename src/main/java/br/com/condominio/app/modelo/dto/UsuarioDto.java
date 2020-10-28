package br.com.condominio.app.modelo.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UsuarioDto {
	
	
 private String password;
 private String username;
 private List<GrantedAuthority> authorities;
 private boolean accountNonExpired;
 private String accountNonLocked;
 private String credentialsNonExpired;
 private String enabled;
 private String token;


 
public UsuarioDto() {


}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}


public List<GrantedAuthority> getAuthorities() {
	return authorities;
}
public void setAuthorities(List<GrantedAuthority> authorities) {
	this.authorities = authorities;
}
public boolean getAccountNonExpired() {
	return accountNonExpired;
}
public void setAccountNonExpired(boolean accountNonExpired) {
	this.accountNonExpired = accountNonExpired;
}
public String getAccountNonLocked() {
	return accountNonLocked;
}
public void setAccountNonLocked(String accountNonLocked) {
	this.accountNonLocked = accountNonLocked;
}
public String getCredentialsNonExpired() {
	return credentialsNonExpired;
}
public void setCredentialsNonExpired(String credentialsNonExpired) {
	this.credentialsNonExpired = credentialsNonExpired;
}
public String getEnabled() {
	return enabled;
}
public void setEnabled(String enabled) {
	this.enabled = enabled;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}




}
