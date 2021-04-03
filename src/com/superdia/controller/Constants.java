package com.superdia.controller;

public class Constants {
	// EJB CONNECTION
	public static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	public static final String PROVIDER_URL = "http-remoting://localhost:8080";
	public static final String SECURITY_PRINCIPAL = "admin";
	public static final String SECURITY_CREDENTIALS = "12345678";
	public static final String EJB_LOOKUP_SERVICE_CLIENT = "ejb:SuperDiaEAR/SuperDia/ServicosClienteBean!br.com.superdia.sessionbeans.IServicosCliente?stateful";
	
	// TITLE UI
} 
