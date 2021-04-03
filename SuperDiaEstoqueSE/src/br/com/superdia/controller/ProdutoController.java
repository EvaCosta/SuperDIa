package br.com.superdia.controller;

import java.util.List;

import br.com.superdia.modelo.Produto;
import br.com.superdia.sessionbeans.IServicosAdmin;

public class ProdutoController {

	private IServicosAdmin admin;
	
	
	public ProdutoController(IServicosAdmin admin) {
		this.admin = admin;
	}


	public void add(Produto product) {
		admin.adicionaProduto(product);
	}
	
	public void update(Produto product) {

		admin.alteraProduto(product);
				
	}
	
	public void remove(Produto product) {
		admin.removeProduto(product);
	}
	
	public List<Produto> obterProdutos(){
		return admin.listaProdutos();
	}


	public int salvarProdutosExternos(List<Produto> produtosExternos) {
		int cont = 0;
		List<Produto> produtos = admin.listaProdutos();
		for (Produto produto : produtosExternos) {
			if(!jaExiste(produto, produtos)) {
				
				
				System.out.println(admin.adicionaProduto(produto));
				
				cont++;
			}
		}
		
		return cont;
		
	}
	
	private boolean jaExiste(Produto produto, List<Produto> produtos) {
		for(Produto p : produtos) {
			if(p.getNome().equals(produto.getNome()))
				return true;
		}
		return false;
	}
}
