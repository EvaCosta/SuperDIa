package br.com.superdia.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.superdia.dto.ProdutosExternos;
import br.com.superdia.dto.ProdutosExternos.Product;
import br.com.superdia.modelo.Produto;

public class ClienteExterno {
	
	
	public List<Produto> obterProdutosExternos(String link) throws IOException {
	
		URL url = new URL(link);
		InputStream in = url.openStream();
	
		String json = new String(in.readAllBytes(), StandardCharsets.UTF_8);
		
		
		ObjectMapper mapper = new ObjectMapper();

		ProdutosExternos produtosExternos =
				mapper.readValue(json, ProdutosExternos.class);
	
		
		return produtosExternos.products.stream().map(p -> map(p)).collect(Collectors.toList());
		
		
	}
	
	private Produto map(Product p) {
		Produto produto = new Produto();
		produto.setNome(p.title);
		produto.setDescricao(p.product_type);
		produto.setPreco(p.variants.get(0).price);
		produto.setQuantidadeEstoque(0);
		produto.setEstoqueMinimo(0);
		return produto;
	}
}
