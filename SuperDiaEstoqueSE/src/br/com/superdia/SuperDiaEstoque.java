package br.com.superdia;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.superdia.controller.ProdutoController;
import br.com.superdia.controller.UsuarioController;
import br.com.superdia.ui.UILogin;

public class SuperDiaEstoque {

	ProdutoController productController;
	UsuarioController userController;
	
	public SuperDiaEstoque() {
		productController = new ProdutoController();
		userController = new UsuarioController();
	}
	
	public static void main(String[] args) {
		new SuperDiaEstoque().iniciar();

	}

	private void iniciar() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, 
					"Ocorreu um erro ao carregar a interface gráfica.\n"
					+ "A aplicação prosseguirá com um estilo de interface diferente.", 
					"Super Dia", JOptionPane.ERROR_MESSAGE);
		}
		new UILogin(userController, productController);
		//new UIPrincipal(controller);
		
	}

}
