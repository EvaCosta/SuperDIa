package br.com.superdia;

import br.com.superdia.controller.ProductController;
import br.com.superdia.ui.UIPrincipal;

public class SuperDiaEstoque {

	ProductController controller;
	
	public SuperDiaEstoque() {
		controller = new ProductController();
	}
	
	public static void main(String[] args) {
		new SuperDiaEstoque().iniciar();

	}

	private void iniciar() {
		new UIPrincipal(controller);
		
	}

}
