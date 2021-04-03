package br.com.superdia.util;

import javax.ejb.EJB;

import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosCliente;

public class Singleton {
	private static Usuario usuarioLogado;
	@EJB
	private static IServicosCliente servico;
	
	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public static void setUsuarioLogado(Usuario usuarioLogado) {
		Singleton.usuarioLogado = usuarioLogado;
	}
	
	public static IServicosCliente getServico() {
		return servico;
	}
	
	public static void setServico(IServicosCliente servico) {
		Singleton.servico = servico;
	}
	
	
	
}
