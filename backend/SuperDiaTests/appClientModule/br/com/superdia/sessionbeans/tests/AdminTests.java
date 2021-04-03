package br.com.superdia.sessionbeans.tests;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.superdia.modelo.Produto;
import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosAdmin;

class AdminTests {
	static IServicosAdmin servicosAdmin;
	static Random r = new Random();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		InitialContext ic = new InitialContext(props);
		servicosAdmin = (IServicosAdmin)ic.lookup("ejb:SuperDiaEAR/SuperDia/ServicosAdminBean!br.com.superdia.sessionbeans.IServicosAdmin?stateful");	
	}
	
	@Test
	void loginAdminSuccessfull() {
		Usuario usuario = new Usuario();
		usuario.setSenha("admin");
		usuario.setUsuario("admin");
		usuario = servicosAdmin.autentica(usuario);
		Assert.assertNotNull(usuario);
	}
	
	@Test
	void adminAddProdutosSuccessfull() {
		Usuario usuario = new Usuario();
		usuario.setSenha("123456");
		usuario.setUsuario("caixa1");
		usuario = servicosAdmin.autentica(usuario);
		
		Produto produto = new Produto();
		produto.setNome(UUID.randomUUID().toString());
		produto.setDescricao(UUID.randomUUID().toString());
		
		double randomValue = 1 + (9999 - 1) * r.nextDouble();
		produto.setPreco(randomValue);
		
		produto.setEstoqueMinimo(1);
		
		int randomInt = r.nextInt(1000);
		if (randomInt < 0) {
			randomInt *= -1;
		}
		produto.setQuantidadeEstoque(1 + (9999 - 1) * r.nextInt());
		
		Assert.assertTrue(servicosAdmin.adicionaProduto(produto));
	}
	
	@Test
	void adminListaProdutosSuccessfull() {
		Usuario usuario = new Usuario();
		usuario.setSenha("123456");
		usuario.setUsuario("caixa1");
		usuario = servicosAdmin.autentica(usuario);
		Assert.assertNotNull(servicosAdmin.listaProdutos());
	}
}
