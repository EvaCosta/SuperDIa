package br.com.superdia.sessionbeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.Usuario;

@Stateful
@Remote(ICarrinho.class)
public class CarrinhoBean implements ICarrinho{

	private List<ItemCarrinho> carrinho = new ArrayList<ItemCarrinho>();
	
	@EJB
	IProduto iProduto;
	
	//@EJB
	//IRegistroVenda iRegistroVenda;
	
	@Override
	public boolean adiciona(ItemCarrinho item) {

		for(ItemCarrinho itemCarrinho: carrinho) {
			if(itemCarrinho.getProduto().equals(item.getProduto())) {
				
				if(item.getQuantidade() + itemCarrinho.getQuantidade() > item.getProduto().getQuantidadeEstoque()) {
					return false;
				}
				
				itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + item.getQuantidade());
				return true;
			}
		}
		carrinho.add(item);
		return true;
		
	}

	@Override
	public boolean remove(ItemCarrinho item) {
		return carrinho.remove(item);
	}


	@Override
	public boolean altera(ItemCarrinho item) {

		if(carrinho.contains(item) && item.getProduto().getQuantidadeEstoque() > item.getQuantidade()) {
			carrinho.remove(item);
			carrinho.add(item);
			return true;
		}
		return false;
	}

	@Override
	public List<ItemCarrinho> lista() {
		return carrinho;
	}

	@Override
	public boolean finalizaCompra(Usuario usuario) {
		/*
		//Verifica se todos os itens tem a quantidade necessária em estoque
		for(ItemCarrinho item: carrinho) {
			Produto produto = iProduto.buscaPorId(item.getProduto().getId());
			if(produto.getQuantidadeEstoque() < item.getQuantidade()) {
				return false;
			}
		}
		
		//Subtrai cada item no carrinho do estoque
		for(ItemCarrinho item: carrinho) {
			Produto produto = iProduto.buscaPorId(item.getProduto().getId());
			Integer quantidadeEmEstoque = produto.getQuantidadeEstoque();
			produto.setQuantidadeEstoque(quantidadeEmEstoque - item.getQuantidade());
			iProduto.altera(produto);
		}
		
		RegistroVenda registro = new RegistroVenda();
		registro.setUsuario(usuario);
		registro.setItens(carrinho);
		
		iRegistroVenda.adiciona(registro);
		
		carrinho.clear();*/
		return true;
	}


}
