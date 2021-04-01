package br.com.superdia.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutosExternos {
	public ProdutosExternos() {
		
	}
	
	public List<Product> products;
    
	
	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Product{
		public Product() {
			
		}
		
		public long id;
	    public String title;
	    public String handle;
	    public String body_html;
	    public Date published_at;
	    public Date created_at;
	    public Date updated_at;
	    public String vendor;
	    public String product_type;
	    public List<Variant> variants;
		
	    public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getHandle() {
			return handle;
		}
		public void setHandle(String handle) {
			this.handle = handle;
		}
		public String getBody_html() {
			return body_html;
		}
		public void setBody_html(String body_html) {
			this.body_html = body_html;
		}
		public Date getPublished_at() {
			return published_at;
		}
		public void setPublished_at(Date published_at) {
			this.published_at = published_at;
		}
		public Date getCreated_at() {
			return created_at;
		}
		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}
		public Date getUpdated_at() {
			return updated_at;
		}
		public void setUpdated_at(Date updated_at) {
			this.updated_at = updated_at;
		}
		public String getVendor() {
			return vendor;
		}
		public void setVendor(String vendor) {
			this.vendor = vendor;
		}
		public String getProduct_type() {
			return product_type;
		}
		public void setProduct_type(String product_type) {
			this.product_type = product_type;
		}
	    
	    
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Variant{
		public Long id;
		public String title;
		public double price;
		public boolean available;
	}


}
