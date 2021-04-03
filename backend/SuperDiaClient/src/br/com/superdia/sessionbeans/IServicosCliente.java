package br.com.superdia.sessionbeans;

import java.util.List;

import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.Produto;
import br.com.superdia.modelo.Usuario;

public interface IServicosCliente {
	Usuario autentica(Usuario usuario);
	boolean adicionaItemCarrinho(ItemCarrinho item);
	boolean removeItemCarrinho(ItemCarrinho item);
	boolean alteraItemCarrinho(ItemCarrinho item);
	List<ItemCarrinho> listaItensCarrinho();
	List<Produto> listaProdutos();
	Produto buscaProdutoPorId(Long id);
	boolean finalizaCompra(String cardNumber);
	boolean validaCartao(String cardNumber);
}
