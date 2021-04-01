package br.com.superdia.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Produto {
	@Id
	@SequenceGenerator(name = "produto_id",
			sequenceName = "produto_seq",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "produto_id")
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer estoqueMinimo;
	private Integer quantidadeEstoque;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}
	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	
	
	
}
