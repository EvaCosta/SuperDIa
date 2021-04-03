package br.com.superdia.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.superdia.modelo.PerfilUsuario;
import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosAdmin;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB {
	@EJB
	private IServicosAdmin usuarioMB;
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	private Usuario usuario = new Usuario();
	private Usuario userLogado;
	private String perfil;
	
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
		return new ArrayList<Usuario>(this.usuarioMB.listaUsuarios());
	}
	
	public void adiciona() {
		if (this.usuario.getId() == null) {
			if (perfil.equals("Admin"))
				this.usuario.setPerfil(PerfilUsuario.ADMIN);
			if (perfil.equals("Caixa"))
				this.usuario.setPerfil(PerfilUsuario.CAIXA);
			if (perfil.equals("Cliente"))
				this.usuario.setPerfil(PerfilUsuario.CLIENTE);
			
			this.usuarioMB.adicionaUsuario(this.usuario);
		}
		else
			altera();
		usuario = new Usuario();
	}
	
	private void altera() {
		this.usuarioMB.alteraUsuario(this.usuario);
	}

	public void remove(Usuario usuario) {
		this.usuarioMB.removeUsuario(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String login() {
		this.userLogado = usuarioMB.autentica(this.usuario);
		
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
