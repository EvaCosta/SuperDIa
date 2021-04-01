package br.com.superdia.controller;

public class Usuario {
	
	private String usuario;
	private String senha;
	private String perfil;
	
	public Usuario() {
		
	}
	
	public Usuario(String usuario, String senha, String perfil) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		this.perfil = perfil;
	}

	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String username) {
		this.usuario = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String password) {
		this.senha = password;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String profile) {
		this.perfil = profile;
	}
	
	
}
