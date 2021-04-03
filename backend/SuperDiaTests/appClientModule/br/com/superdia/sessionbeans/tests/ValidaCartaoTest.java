package br.com.superdia.sessionbeans.tests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.superdia.sessionbeans.IServicosCliente;


public class ValidaCartaoTest {
	static IServicosCliente vc;

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		Properties props = new Properties();
//		props.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
//		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
//		InitialContext ic = new InitialContext(props);
//		vc = (IServicosCliente)ic.lookup("ejb:SuperDiaEAR/SuperDia/ServicosClienteBean!br.com.superdia.sessionbeans.IServicosCliente?stateful");
//	}
	
	private boolean validaCartao(String cardNumber) {
		try {
			
			Client client = ClientBuilder.newClient();
			String urlCep = "https://secure.ftipgw.com/ArgoFire/validate.asmx/ValidMod10?CardNumber=" + cardNumber;
			WebTarget target = client.target(urlCep);
			Response response = target.request().get();
			Boolean resp = Boolean.valueOf(response.readEntity(String.class));

			return resp != null && resp.booleanValue();
			
		}catch (Exception e) {
			return false;
		}
	}
	
	@Test
	void validaCartao() {
		
		
		
		Assert.assertFalse(validaCartao("1235654889844563"));
		Assert.assertTrue(validaCartao("4111111111111111"));
	}

}