package br.com.superdia.controller;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
	
	private List<Produto> products;
	
	
	public ProdutoController() {
		products = new ArrayList<Produto>();
		
		products.add(new Produto("Camisa X", "Item da Marca X", 20.50, 70, 30));
		products.add(new Produto("Camisa Y", "Item da Marca X", 40.50, 60, 20));
		products.add(new Produto("Camisa Z", "Item da Marca X", 60.50, 50, 10));
		
	}


	public List<Produto> getProducts(){
		return products;
	}
	
	public void add(Produto product) {
		products.add(product);
	}
	
	public void update(Produto product) {

		for (int i = 0; i<products.size(); i++) {
			if(products.get(i).getId().equals(product.getId())) {
				products.set(i, product);
				return;
			}	
		}
				
	}
	
	public void remove(Produto product) {
		for (int i = 0; i<products.size(); i++) {
			if(products.get(i).getId().equals(product.getId())) {
				products.remove(i);
				return;
			}
		}
	}
}
