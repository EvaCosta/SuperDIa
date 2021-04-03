package com.prova.controller;

public class Constants {
	// EJB CONNECTION
	public static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	public static final String PROVIDER_URL = "http-remoting://localhost:8080";
	public static final String SECURITY_PRINCIPAL = "admin";
	public static final String SECURITY_CREDENTIALS = "12345678";
	public static final String EJB_LOOKUP_SERVICE_CLIENT = "ejb:SuperDiaEAR/SuperDia/ServicosClienteBean!br.com.superdia.sessionbeans.IServicosCliente?stateful";
	
	// TITLE UI
	public static final String APPLICATION_NAME = "Superdia Caixa";
	public static final String LOGIN_TITLE = String.format("%s - %s", APPLICATION_NAME, "Login");
	public static final String REGISTER_PURCHASE_TITLE = String.format("%s", APPLICATION_NAME);
	
	// MENU
	public static final String EXIT = "Sair";
	public static final String MENU = "Menu";
	public static final String SUPER_DIA_MENU = "Superdia";
	
	// IMAGE PATH
	public static final String FAVICON = "/assets/favicons/favicon-32x32.png";
	public static final String LOGO_ALT_VERTICAL = "/assets/logos/superdia_logo_alt_vert.png";
	public static final String LOGO_ALT = "/assets/logos/superdia_logo_alt.png";
	public static final String LOGO = "/assets/logos/superdia_logo.png";
	public static final String CART_IMAGE = "/assets/icons/cart.png";
	public static final String EXIT_IMAGE = "/assets/icons/exit.png";
	
	// LOGIN
	public static final String LABEL_LOGIN = "Login";
	public static final String LABEL_PASSWORD = "Senha";
	public static final String BUTTON_SIGN_IN = "Entrar";
	
	// REGISTER PURCHASE
	public static final String[] COLUMNS_PRODUCTS_AVAILABLE_TABLE = {"ID", "Produto", "Descrição", "Quantidade em Estoque", "Preço"};
	public static final String[] COLUMNS_PRODUCTS_PURCHASE_TABLE = {"ID", "Produto", "Descrição", "Quantidade no carrinho", "Valor Total"};
	public static final String ADD_CART = "Adicionar";
	public static final String AVAILABLE_PRODUCTS = "Produtos disponíveis";
	public static final String SAVE_PURCHASE = "Registrar compra";
	public static final String BACK_DASHBOARD = "Cancelar";
	public static final String PRODUCTS_ON_CART = "Produtos no carrinho";
	public static final String SELECTED_PRODUCT = "Produto selecionado";
	public static final String REMOVE_CART = "Remover do carrinho";
} 
