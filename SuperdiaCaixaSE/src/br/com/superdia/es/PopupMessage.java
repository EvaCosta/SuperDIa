package br.com.superdia.es;

import static javax.swing.JOptionPane.*;

public class PopupMessage {
	public static void messageInformation(String message, String title) {
		showMessageDialog(null, message, title, INFORMATION_MESSAGE);
	}

	public static void messageInformation(Object message, String title) {
		showMessageDialog(null, message, title, INFORMATION_MESSAGE);
	}

	public static void messageError(String message, String title) {
		showMessageDialog(null, message, title, ERROR_MESSAGE);
	}
	
	public static void messageWarning(String message, String title) {
		showMessageDialog(null, message, title, WARNING_MESSAGE);
	}
	
	public static int questionConfirmationDialog(String message, String title) {
		return showConfirmDialog(null, message, title, QUESTION_MESSAGE);
	}
	
	public static int warningConfirmationDialog(String message, String title) {
		return showConfirmDialog(null, message, title, WARNING_MESSAGE);
	}
}
