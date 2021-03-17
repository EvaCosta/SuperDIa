package br.com.superdia.controller;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
	
	private List<Product> products;
	
	
	public ProductController() {
		products = new ArrayList<Product>();
		
		products.add(new Product("Camisa X", "Item da Marca X", 20.50, 70, 30));
		products.add(new Product("Camisa Y", "Item da Marca X", 40.50, 60, 20));
		products.add(new Product("Camisa Z", "Item da Marca X", 60.50, 50, 10));
	}


	public List<Product> getProducts(){
		return products;
	}
	
	public void add(Product product) {
		products.add(product);
	}
	
	public void update(Product product) {

		for (int i = 0; i<products.size(); i++) {
			if(products.get(i).getId().equals(product.getId())) {
				products.set(i, product);
				return;
			}
		}
				
	}
	
	public void remove(Product product) {
		for (int i = 0; i<products.size(); i++) {
			if(products.get(i).getId() == product.getId()) {
				products.remove(i);
				return;
			}
		}
	}
}
