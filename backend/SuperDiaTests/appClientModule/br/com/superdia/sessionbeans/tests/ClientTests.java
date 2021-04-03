package br.com.superdia.sessionbeans.tests;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.Produto;
import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosCliente;

class ClientTests {
	static InitialContext ic;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
	    ic = new InitialContext(props);
	}

	@Test
	void loginClientSuccessfull() throws NamingException {
		IServicosCliente servicosClient = (IServicosCliente)ic.lookup("ejb:SuperDiaEAR/SuperDia/ServicosClienteBean!br.com.superdia.sessionbeans.IServicosCliente?stateful");
		Usuario usuario = new Usuario();
		usuario.setSenha("123456");
		usuario.setUsuario("caixa1");
		usuario = servicosClient.autentica(usuario);
		Assert.assertNotNull(usuario);
	}
	
	@Test
	void clientListaProdutosSuccessfull()  throws NamingException {
		IServicosCliente servicosClient = (IServicosCliente)ic.lookup("ejb:SuperDiaEAR/SuperDia/ServicosClienteBean!br.com.superdia.sessionbeans.IServicosCliente?stateful");
		Usuario usuario = new Usuario();
		usuario.setSenha("123456");
		usuario.setUsuario("caixa1");
		usuario = servicosClient.autentica(usuario);
		Assert.assertNotNull(servicosClient.listaProdutos());
	}
	
	@Test
	void clientListaItensCarrinho() throws NamingException {
		IServicosCliente servicosClient = (IServicosCliente)ic.lookup("ejb:SuperDiaEAR/SuperDia/ServicosClienteBean!br.com.superdia.sessionbeans.IServicosCliente?stateful");
		Usuario usuario = new Usuario();
		usuario.setSenha("123456");
		usuario.setUsuario("caixa1");
		usuario = servicosClient.autentica(usuario);
		Produto prod = servicosClient.listaProdutos().get(0);
		ItemCarrinho car = new ItemCarrinho();
		car.setProduto(prod);
		servicosClient.adicionaItemCarrinho(car);
		Assert.assertNotNull(servicosClient.listaItensCarrinho());
		Assert.assertTrue(servicosClient.listaItensCarrinho().size() > 0);
	}
	
	@Test
	void clientRemoveItemCarrinho() throws NamingException {
		IServicosCliente servicosClient = (IServicosCliente)ic.lookup("ejb:SuperDiaEAR/SuperDia/ServicosClienteBean!br.com.superdia.sessionbeans.IServicosCliente?stateful");
		Usuario usuario = new Usuario();
		usuario.setSenha("123456");
		usuario.setUsuario("caixa1");
		usuario = servicosClient.autentica(usuario);
		Produto prod = servicosClient.listaProdutos().get(0);
		ItemCarrinho car = new ItemCarrinho();
		car.setQuantidade(1);
		car.setProduto(prod);
		servicosClient.adicionaItemCarrinho(car);
		List<ItemCarrinho> list = servicosClient.listaItensCarrinho();
		Assert.assertNotNull(list);
		Assert.assertTrue(servicosClient.listaItensCarrinho().size() > 0);
		
		ItemCarrinho fodase = new ItemCarrinho();
		fodase.setQuantidade(1);
		fodase.setProduto(servicosClient.listaProdutos().get(0));		
		Assert.assertTrue(servicosClient.removeItemCarrinho(fodase));
		
	    list = servicosClient.listaItensCarrinho();
		
		Assert.assertTrue(list.size() == 0);
	}
}
