package br.com.superdia.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.superdia.modelo.Produto;
import br.com.superdia.sessionbeans.IServicosAdmin;
import br.com.superdia.sessionbeans.IServicosCliente;

@ManagedBean(name = "produtoMB")
@SessionScoped
public class ProdutoMB {
	private Produto produto = new Produto();
	private int qtdEstoque = 0;
	@EJB
	private IServicosAdmin produtoMB;
	
	private List<Produto> produtos;
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void grava() {
		if(produto.getId() == null) {
			System.out.println("Adicionando produtos...");
			produtoMB.adicionaProduto(produto);
		}else {
			System.out.println("Alterando produtos...");
			produtoMB.alteraProduto(this.produto);
		}
		this.produto = new Produto();
	}
	
	public void cancela() {
		this.produto = new Produto();
	}
	
	public List<Produto> getProdutos(){
		return produtoMB.listaProdutos();
	}
		
	public void remove(Produto produto) {
		System.out.println("Excluindo produtos...");
		produtoMB.removeProduto(produto);
	}
	
	public void setProdutos(List<Produto> produtos) {
		
	}
	
}
