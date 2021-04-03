package br.com.superdia.sessionbeans;

import java.util.List;

import br.com.superdia.modelo.Produto;
import br.com.superdia.modelo.RegistroVenda;
import br.com.superdia.modelo.Usuario;

public interface IServicosAdmin {
	Usuario autentica(Usuario usuario);
	boolean adicionaProduto(Produto produto);
	boolean removeProduto(Produto produto);
	boolean alteraProduto(Produto produto);
	List<Produto> listaProdutos();
	boolean adicionaUsuario(Usuario usuario);
	boolean removeUsuario(Usuario usuario);
	boolean alteraUsuario(Usuario usuario);
	List<Usuario> listaUsuarios();
	boolean removeVenda(RegistroVenda registroVenda);
	List<RegistroVenda> listaVendas();
}
