package br.com.superdia;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.superdia.controller.ProdutoController;
import br.com.superdia.controller.UsuarioController;
import br.com.superdia.controller.VendasController;
import br.com.superdia.sessionbeans.IServicosAdmin;
import br.com.superdia.ui.UILogin;
import br.com.superdia.ui.UIVendas;

public class SuperDiaEstoque {

	ProdutoController productController;
	UsuarioController userController;
	VendasController vendasController;
	
	public SuperDiaEstoque() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, 
					"Ocorreu um erro ao carregar a interface gráfica.\n"
					+ "A aplicação prosseguirá com um estilo de interface diferente.", 
					"Super Dia", JOptionPane.ERROR_MESSAGE);
		}
		
		
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		props.put(Context.SECURITY_PRINCIPAL, "admin");
		props.put(Context.SECURITY_CREDENTIALS, "123456");
		
		InitialContext ic;
//		IUsuario usuario;
//		IProduto produto;
//		IRegistroVenda vendas;
		
		IServicosAdmin servicos;
		
		try {
			ic = new InitialContext(props);

			servicos = (IServicosAdmin)
					ic.lookup("ejb:SuperDiaEAR/SuperDia/ServicosAdminBean!br.com.superdia.sessionbeans.IServicosAdmin?stateful");
					
			
		} catch (NamingException e) {
			JOptionPane.showMessageDialog(null, 
					"Ocorreu um erro ao conectar-se ao banco de dados.", 
					"Super Dia", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
		
		

		productController = new ProdutoController(servicos);
		userController = new UsuarioController(servicos);
		vendasController = new VendasController(servicos);
	}
	
	public static void main(String[] args) {
		new SuperDiaEstoque().iniciar();

	}

	private void iniciar() {
		
		new UILogin(userController, productController, vendasController);
		new UIVendas(vendasController);
		
	}

}
