package br.com.superdia.sessionbeans;

import java.util.List;

import br.com.superdia.modelo.Produto;

public interface IProduto {
	boolean adiciona(Produto produto);
	boolean remove(Produto produto);
	boolean altera(Produto produto);
	List<Produto> lista();
	Produto buscaPorId(Long id);
}
