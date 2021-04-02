package br.com.superdia.controller;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.superdia.sessionbeans.ICarrinho;
import br.com.superdia.sessionbeans.IProduto;
import br.com.superdia.sessionbeans.IRegistroVenda;
import br.com.superdia.sessionbeans.IUsuario;

public class Singleton {
	private static InitialContext ic;

	public static InitialContext getInitialContext() {
		return ic;
	}

	public static void setInitialContext(InitialContext ic) {
		Singleton.ic = ic;
	}
	
	public static IUsuario getIUsuario() throws NamingException {
		return (IUsuario) ic.lookup(Constants.EJB_LOOKUP_USER);
	}
	
	public static IProduto getIProduto() throws NamingException {
		return (IProduto) ic.lookup(Constants.EJB_LOOKUP_PRODUCT);
	}
	
	public static ICarrinho getICarrinho() throws NamingException {
		return (ICarrinho) ic.lookup(Constants.EJB_LOOKUP_CART);
	}
	
	public static IRegistroVenda getIRegistroVenda() throws NamingException {
		return (IRegistroVenda) ic.lookup(Constants.EJB_LOOKUP_REGISTER_PURCHASE);
	}
}
