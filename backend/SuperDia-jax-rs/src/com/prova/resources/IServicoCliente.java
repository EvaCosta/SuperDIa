package com.prova.resources;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prova.controller.Singleton;

import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.Produto;
import br.com.superdia.modelo.Usuario;

@Path("/servico-cliente")
public class IServicoCliente {
	

	@GET
	@Path("/lista-produtos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listaProduto() throws NamingException{
		return Singleton.getIServicosCliente().listaProdutos();
	}
	
	@POST
	@Path("/autentica")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario Autenticar(Usuario usuario) throws NamingException {
		return Singleton.getIServicosCliente().autentica(usuario);
	}
	
	@GET
	@Path("/carrinho")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemCarrinho> listaItensCarrinho( ) throws NamingException {
		return Singleton.getIServicosCliente().listaItensCarrinho();
	}
	
	@POST
	@Path("/carrinho")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean adicionaItemCarrinho(ItemCarrinho itemCarrinho) throws NamingException {
		return Singleton.getIServicosCliente().adicionaItemCarrinho(itemCarrinho);
	}
	
	@DELETE
	@Path("/carrinho")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean removeItemCarrinho(ItemCarrinho itemCarrinho) throws NamingException {
		return Singleton.getIServicosCliente().removeItemCarrinho(itemCarrinho);
	}
	
	@POST
	@Path("/carrinho/finaliza")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean finalizaCompra() throws NamingException {
		return Singleton.getIServicosCliente().finalizaCompra("asdf");
	}
	
	
	
}
