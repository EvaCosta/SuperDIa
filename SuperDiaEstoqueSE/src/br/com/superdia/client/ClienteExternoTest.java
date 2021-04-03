package br.com.superdia.client;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.superdia.modelo.Produto;

public class ClienteExternoTest {

	@Test
	void testObterProdutosExternos() {
		try {
			List<Produto> externos = new ClienteExterno()
					.obterProdutosExternos("https://www.kyliecosmetics.com/products.json");
			
			System.out.println(externos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

}
