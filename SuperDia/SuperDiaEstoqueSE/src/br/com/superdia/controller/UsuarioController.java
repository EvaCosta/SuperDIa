package br.com.superdia.controller;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
	
private List<Usuario> users;
	
	
	public UsuarioController() {
		users = new ArrayList<Usuario>();
		
		users.add(new Usuario("admin", "admin", "admin"));
		users.add(new Usuario("ramon", "ramon", "cliente"));
		users.add(new Usuario("jooj", "jooj", "caixa"));
		
	}
	
	
	public boolean login(Usuario user) {
		for (Usuario u : users) {
			if(user.getUsuario().equals(u.getUsuario())
					&& user.getSenha().equals(u.getSenha())
					&& u.getPerfil().equals("admin")) 
			{
				return true;
			}
		}
		
		return false;
	}
}
