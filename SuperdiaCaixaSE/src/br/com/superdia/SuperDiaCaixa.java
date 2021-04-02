package br.com.superdia;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.superdia.controller.Constants;
import br.com.superdia.controller.Singleton;
import br.com.superdia.ui.UILogin;

public class SuperDiaCaixa {
	public static void main(String[] args) {
		try {
			new SuperDiaCaixa().start();			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void start() throws NamingException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, Constants.INITIAL_CONTEXT_FACTORY);
		props.put(Context.PROVIDER_URL, Constants.PROVIDER_URL);
		props.put(Context.SECURITY_PRINCIPAL, Constants.SECURITY_PRINCIPAL);
		props.put(Context.SECURITY_CREDENTIALS, Constants.SECURITY_CREDENTIALS);
		
		InitialContext ic = new InitialContext(props);
		Singleton.setInitialContext(ic);
		
		new UILogin();
	}
}
