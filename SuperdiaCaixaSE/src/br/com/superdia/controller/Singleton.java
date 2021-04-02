package br.com.superdia.controller;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.superdia.sessionbeans.IServicosCliente;

public class Singleton {
	private static InitialContext ic;
	private static IServicosCliente iServicesClient;

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
}
