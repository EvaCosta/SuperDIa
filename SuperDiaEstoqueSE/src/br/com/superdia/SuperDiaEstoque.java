package br.com.superdia;

import br.com.superdia.controller.ProductController;
import br.com.superdia.controller.UserController;
import br.com.superdia.ui.UILogin;

public class SuperDiaEstoque {

	ProductController productController;
	UserController userController;
	
	public SuperDiaEstoque() {
		productController = new ProductController();
		userController = new UserController();
	}
	
	public static void main(String[] args) {
		new SuperDiaEstoque().iniciar();

	}

	private void iniciar() {
		
		new UILogin(userController, productController);
		//new UIPrincipal(controller);
		
	}

}
