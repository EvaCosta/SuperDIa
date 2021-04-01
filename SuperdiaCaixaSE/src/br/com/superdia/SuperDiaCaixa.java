package br.com.superdia;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

	public void start() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		new UILogin();
	}
}
