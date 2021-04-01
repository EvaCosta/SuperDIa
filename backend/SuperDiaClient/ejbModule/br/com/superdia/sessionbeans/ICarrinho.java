package br.com.superdia.sessionbeans;

import br.com.superdia.modelo.ItemCarrinho;

public interface ICarrinho {
	boolean adiciona(ItemCarrinho item);
	boolean remove(ItemCarrinho item);
	boolean altera(ItemCarrinho item);
	boolean finalizaCompra();
}
