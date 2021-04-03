package br.com.superdia.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.RegistroVenda;
import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosAdmin;

public class VendasController {

	private IServicosAdmin servicos;

	private List<RegistroVenda> registros = new ArrayList<RegistroVenda>();
	
	public VendasController(IServicosAdmin servicos) {
		
		this.servicos = servicos;
	}
	
	
	public boolean removeVenda(RegistroVenda registro) {
		
		return servicos.removeVenda(registro);
	}
	
	
	public String obterItensCarrinho(Long idVenda, Long idUsuario) {
		
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		String text = "Itens comprados\n";
		
		List<RegistroVenda> registros = servicos.listaVendas();
		
		for(RegistroVenda r : registros) {
			if(r.getId().equals(idVenda) && r.getUsuario().getId().equals(idUsuario)) {
				for(ItemCarrinho c : r.getItens()) 
					text += c.getProduto().getNome() + " x" + c.getQuantidade(); 
			}
		}
		
		return text;
	}
	
	public void atualizar() {
		registros = servicos.listaVendas();
	}
	
	public List<RegistroVenda> listaRegistros(){
		if(registros.isEmpty())
			registros = servicos.listaVendas();
		return registros;
	}
}
