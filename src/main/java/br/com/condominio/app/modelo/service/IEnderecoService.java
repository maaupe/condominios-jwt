package br.com.condominio.app.modelo.service;

import br.com.condominio.app.modelo.Endereco;

public interface IEnderecoService {
		public void salvar(Endereco endereco);
		public Endereco alterar(Endereco endereco);
		public void deletarPorid(Long idEndereco);
		public Endereco BuscaPorId(Long idEndereco);
}


