package br.com.superdia.mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.superdia.modelo.PerfilUsuario;
import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosCliente;
import br.com.superdia.util.Singleton;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB {
	@EJB
	private IServicosCliente cliente;
	
	public IServicosCliente getCliente() {
		
		return cliente;
	}

	public void setCliente(IServicosCliente cliente) {
		this.cliente = cliente;
	}

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
	

	public void adiciona() {
		if (this.usuario.getId() == null) {
			if (perfil.equals("Cliente"))
				this.usuario.setPerfil(PerfilUsuario.CLIENTE);
			
			//this.usuarioMB.adicionaUsuario(this.usuario);
		}
		
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String login() {
		this.userLogado = cliente.autentica(this.usuario);
		
		if (this.userLogado != null) {
			usuario = new Usuario();
			Singleton.setUsuarioLogado(userLogado);
			Singleton.setServico(cliente);
			return "menu";
		}
		else
			return "login";
	}
	
	public String logout() {
		return "login";
	}
	
	public String menu() {
		return "menu";
	}
	
	public String iniciarCompra() {
		return "carrinho";
	}
	
} // class UsuarioMB
