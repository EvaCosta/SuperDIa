package br.com.superdia.controller;

public class Constants {
	public static final String APPLICATION_NAME = "Superdia Caixa";
	public static final String LOGIN_TITLE = String.format("%s - %s", APPLICATION_NAME, "Login");
	public static final String DASHBOARD_TITLE = String.format("%s - %s", APPLICATION_NAME, "Dashboard");
	
	// LOGIN
	public static final String LABEL_LOGIN = "Login";
	public static final String LABEL_PASSWORD = "Senha";
	public static final String BUTTON_SIGN_IN = "Entrar";
	
	// DASHBOARD
	public static final String[] COLUMNS_PURCHASE_TABLE = {"Nome", "Pagamento", "Preço", "Status"};
	public static final String MENU = "Menu";
	public static final String EXIT = "Sair";
	public static final String SUPER_DIA_MENU = "Superdia";
	public static final String REGISTER_PURCHASE = "Cadastrar compra";
} 
