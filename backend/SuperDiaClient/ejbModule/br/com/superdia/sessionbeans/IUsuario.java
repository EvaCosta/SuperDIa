package br.com.superdia.sessionbeans;

import java.util.List;

import br.com.superdia.modelo.Usuario;

public interface IUsuario {
	boolean adiciona(Usuario usuario);
	boolean remove(Usuario usuario);
	boolean altera(Usuario usuario);
	List<Usuario> getUsuarios();
}
 