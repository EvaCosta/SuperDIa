package br.com.superdia.sessionbeans;

import java.util.List;

import br.com.superdia.modelo.RegistroVenda;
import br.com.superdia.modelo.Usuario;

public interface IRegistroVenda {
	boolean adiciona(RegistroVenda registro);
	boolean remove(RegistroVenda registro);
	boolean altera(RegistroVenda registro);
	List<RegistroVenda> lista();
	List<RegistroVenda> listaPorUsuario(Usuario usuario);
}
