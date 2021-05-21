package br.com.condominio.app.modelo.service;

import java.util.Optional;

import br.com.condominio.app.modelo.Usuario;

public interface IUsuarioService {

	public Usuario create(Usuario usuario);
	public Usuario getUsuario(String nome);
	public Optional getUsuarioLogin(String login);	
	
}
