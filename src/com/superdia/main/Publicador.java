package com.superdia.main;

import java.net.URI;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;
import com.superdia.controller.Constants;
import com.superdia.controller.Singleton;



public class Publicador {
	

	public static void main(String[] args) {
		ResourceConfig rc = new ResourceConfig().packages(new String[] {"com.superdia.resources"});
		HttpServer httpServer = JdkHttpServerFactory.createHttpServer(URI.create("http://localhost:8081/"), rc);
		System.out.println("Servidor escutando em http://localhost:8081");
		
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, Constants.INITIAL_CONTEXT_FACTORY);
		props.put(Context.PROVIDER_URL, Constants.PROVIDER_URL);
		props.put(Context.SECURITY_PRINCIPAL, Constants.SECURITY_PRINCIPAL);
		props.put(Context.SECURITY_CREDENTIALS, Constants.SECURITY_CREDENTIALS);
		
		InitialContext ic;
		try {
			ic = new InitialContext(props);
			Singleton.setInitialContext(ic);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
