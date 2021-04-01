package br.com.superdia.modelo;

import java.io.Serializable;

public class ItemCarrinho implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4099544414991324406L;
	
	private Produto produto;
	private Integer quantidade;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
