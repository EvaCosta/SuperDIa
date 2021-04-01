package br.com.superdia.modelo;


public class Loja {
	
	Loja(int id, String nome, String link) {
		this.id = id;
		this.nome = nome;
		this.link = link;
	}
	
	private int id;
	private String nome;
	private String link;
	
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getLink() {
		return link;
	}
}
