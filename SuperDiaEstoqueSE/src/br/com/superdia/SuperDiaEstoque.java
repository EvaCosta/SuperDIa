package br.com.superdia;

import br.com.superdia.controller.ProductController;
import br.com.superdia.ui.UILogin;

public class SuperDiaEstoque {

	ProductController controller;
	
	public SuperDiaEstoque() {
		controller = new ProductController();
	}
	
	public static void main(String[] args) {
		new SuperDiaEstoque().iniciar();

	}

	private void iniciar() {
		
		new UILogin();
		//new UIPrincipal(controller);
		
	}

}
