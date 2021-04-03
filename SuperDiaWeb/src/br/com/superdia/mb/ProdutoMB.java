package br.com.superdia.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.Produto;
import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosAdmin;
import br.com.superdia.sessionbeans.IServicosCliente;

@ManagedBean(name = "produtoMB")
@ViewScoped
public class ProdutoMB {
	
	private Produto produto = new Produto();
	
	
	private List<Produto> produtos;
	
	
	
	private List<ItemCarrinho> carrinho;
	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void cancela() {
		this.produto = new Produto();
	}
	
	public List<Produto> getProdutos(IServicosCliente cliente){
		return cliente.listaProdutos();
	}

	
	public void setProdutos(List<Produto> produtos) {
		
	}
	
	public void adiciona(Produto produto, IServicosCliente cliente) {
		ItemCarrinho ic = new ItemCarrinho();
		
		ic.setProduto(produto);
		ic.setQuantidade(1);
		cliente.adicionaItemCarrinho(ic);
		System.out.println(cliente.listaItensCarrinho());
	}

	public List<ItemCarrinho> getCarrinho(IServicosCliente cliente) {
		return cliente.listaItensCarrinho();
	}

	public void setCarrinho(List<ItemCarrinho> carrinho) {
		this.carrinho = carrinho;
	}
	
	public void remove(ItemCarrinho item, IServicosCliente cliente) {
		cliente.removeItemCarrinho(item);
	}
	
	public String finaliza(IServicosCliente cliente, Usuario usuario) {
		if(cliente.finalizaCompra(usuario)) 
			return "sucesso";
		return "falha";
	}
	
	
}
