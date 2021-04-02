package br.com.superdia.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IUsuario;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB {
	@EJB
	private IUsuario usuarioMB;
	
	private Usuario usuario = new Usuario();
	private Usuario userLogado;
	
	public Usuario getUserLogado() {
		return userLogado;
	}

	public void setUserLogado(Usuario userLogado) {
		this.userLogado = userLogado;
	}

	public String iniciar() {
		return "main";
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return new ArrayList<Usuario>(this.usuarioMB.lista());
	}
	
	public void adiciona() {
		if (this.usuario.getId() == null)
			this.usuarioMB.adiciona(this.usuario);
		else
			altera();
		usuario = new Usuario();
	}
	
	private void altera() {
		this.usuarioMB.altera(this.usuario);
	}

	public void remove(Usuario usuario) {
		this.usuarioMB.remove(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String login() {
		this.userLogado = usuarioMB.login(this.usuario);
		
		if (this.userLogado != null) {
			usuario = new Usuario();
			return "usuario";
			
		}
		else
			return "login";
	}
	
	public String logout() {
		return "login";
	}
	
} // class UsuarioMB
