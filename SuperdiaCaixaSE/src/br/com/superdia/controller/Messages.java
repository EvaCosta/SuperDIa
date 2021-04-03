package br.com.superdia.controller;

public class Messages {
	// LOGIN ERRORS
	public static final String ERROR_LOGIN_MESSAGE = "Ocorreu um problema no login. Verifique seu usuário/senha.";
	public static final String ERROR_LOGIN_EJB = "Ocorreu uma falha critica no login. Por favor, reporte o problema para a equipe de TI.";
	
	// REGISTER PURCHASE ERRORS
	public static final String ERROR_REGISTER_PURCHASE_EJB = "Ocorreu um problema ao tentar carregar Produtos/Carrinho.";
	public static final String ERROR_LOAD_REGISTERS_PURCHASE = "Ocorreu um problema ao tentar carregar os registros de compras.";
	public static final String ERROR_LOAD_PRODUCTS_EJB = "Ocorreu um problema ao tentar carregar Produtos.";
	public static final String HAS_NO_PRODUCTS_REGISTRED = "Nenhum produto cadastrado no sistema.";

	// QUESTIONS MESSAGE
	public static final String CONFIRMATION_CANCEL_PURCHASE = "Deseja realmente cancelar essa compra?";
	public static final String CONFIRMATION_EXIT = "Deseja realmente sair?";
	public static final String CONFIRMATION_PURCHASE = "Deseja cadastrar essa compra?";
	
	// SUCCESS REGISTER PURCHASE
	public static final String SUCCESS_PURCHASE = "Compra realizada com suceesso.";
}
