package com.prova.resources;

import static org.junit.Assert.assertNotNull;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.superdia.modelo.Usuario;

class IServicoClienteTest {

	@BeforeAll
	public static void test() {
		ResourceConfig src = new 
				ResourceConfig().packages(new String[] {"com.prova.resources"});
			JdkHttpServerFactory.createHttpServer
					(URI.create("http://localhost:8087/"), src);
	}

	@Test
	public void getJson() {
		Usuario usuario = new Usuario();
		usuario.setUsuario("user");
		usuario.setSenha("user");
		
		Client client = ClientBuilder.newClient();
	    WebTarget target = client.target("http://localhost:8087/servico-cliente/autentica");
	    Response response = target.request().post(Entity.json(usuario));
	    Usuario result = response.readEntity(Usuario.class);
	    assertNotNull(result);
	}
}
