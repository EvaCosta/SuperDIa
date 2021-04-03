package br.com.superdia.controller;

import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosAdmin;

public class UsuarioController {
	
	
	public UsuarioController(IServicosAdmin admin) {
		this.admin = admin;
	}
	
	private IServicosAdmin admin;

	
	public boolean login(Usuario user) {
		
		return admin.autentica(user) != null;
				
	}
}
