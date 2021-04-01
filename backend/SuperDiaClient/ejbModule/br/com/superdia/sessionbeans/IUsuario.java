package br.com.superdia.sessionbeans;

import java.util.List;

import br.com.superdia.modelo.Usuario;

public interface IUsuario {
	void adiciona(Usuario usuario);
	void remove(Usuario usuario);
	void altera(Usuario usuario);
	List<Usuario> getUsuarios();
}
 