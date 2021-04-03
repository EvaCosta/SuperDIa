package br.com.superdia.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ItemCarrinho implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4099544414991324406L;
	
	@Id
	@SequenceGenerator(name = "item_carrinho_id",
			sequenceName = "item_carrinho_seq",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "item_carrinho_id")
	private Long id;
	
	@OneToOne
	private Produto produto;
	
	private Double preco;
	
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
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
