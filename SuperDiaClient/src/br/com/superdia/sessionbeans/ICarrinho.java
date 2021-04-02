package br.com.superdia.sessionbeans;

import java.util.List;

import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.Usuario;

public interface ICarrinho {
	boolean adiciona(ItemCarrinho item);
	boolean remove(ItemCarrinho item);
	boolean altera(ItemCarrinho item);
	List<ItemCarrinho> lista();
	boolean finalizaCompra(Usuario usuario);
}
