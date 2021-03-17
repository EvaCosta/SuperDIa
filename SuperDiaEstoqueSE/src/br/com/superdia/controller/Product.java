package br.com.superdia.controller;

import java.util.Calendar;

public class Product {
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer quantidade;
	private Integer estoqueMinimo;

	
	
	public Product() {
	}

	public Product(String nome, String descricao, Double preco, Integer quantidade, 
			Integer estoqueMinimo) {
		this.id = (long) (Calendar.getInstance().get(Calendar.MILLISECOND) * preco * quantidade);
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.estoqueMinimo = estoqueMinimo;
	}
	
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
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}
	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	
	
	
}
