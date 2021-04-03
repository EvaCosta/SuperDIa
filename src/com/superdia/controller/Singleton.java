package com.superdia.controller;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosCliente;

public class Singleton {
	private static InitialContext ic;
	private static IServicosCliente iServicesClient;
	private static Usuario user;

	public static InitialContext getInitialContext() {
		return ic;
	}

	public static void setInitialContext(InitialContext ic) {
		Singleton.ic = ic;
	}
	
	public static IServicosCliente getIServicosCliente() throws NamingException {
		iServicesClient = (iServicesClient != null) ? iServicesClient : (IServicosCliente) ic.lookup(Constants.EJB_LOOKUP_SERVICE_CLIENT);
		return iServicesClient;
	}
	
	public static void closeSession() {
		iServicesClient = null;
		user = null;
	}

	public static Usuario getUser() {
		return user;
	}

	public static void setUser(Usuario newUser) {
		user = newUser;
	}
}
