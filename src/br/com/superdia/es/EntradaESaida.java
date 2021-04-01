package br.com.superdia.es;

import static javax.swing.JOptionPane.*;

public class EntradaESaida {
	public static void msgInfo(String message, String title) {
		showMessageDialog(null, message, title, INFORMATION_MESSAGE);
	}

	public static void msgInfo(Object message, String title) {
		showMessageDialog(null, message, title, INFORMATION_MESSAGE);
	}

	public static void msgErro(String message, String title) {
		showMessageDialog(null, message, title, ERROR_MESSAGE);
	}

	public static String lerString(String message, String title) {
		return showInputDialog(null, message, title, INFORMATION_MESSAGE);
	}
	
	public static Float lerFloat(String message, String title) throws NumberFormatException {
		return Float.parseFloat(showInputDialog(null, message, title, INFORMATION_MESSAGE));
	}
	
	public static Integer lerInt(String message, String title) throws NumberFormatException {
		return Integer.parseInt(showInputDialog(null, message, title, INFORMATION_MESSAGE));
	}
}
