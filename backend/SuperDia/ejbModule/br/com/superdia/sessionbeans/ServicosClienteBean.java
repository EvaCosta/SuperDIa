package br.com.superdia.sessionbeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.Produto;
import br.com.superdia.modelo.RegistroVenda;
import br.com.superdia.modelo.Usuario;

@Stateful
@Remote(IServicosCliente.class)
public class ServicosClienteBean implements IServicosCliente{
	@PersistenceContext(unitName = "SuperDia")
	EntityManager em;
	
	private Usuario usuarioLogado;
	private List<ItemCarrinho> carrinho = new ArrayList<ItemCarrinho>();
	

	public Usuario autentica(Usuario usuario) {
		Query query = em.createQuery("from Usuario u where u.usuario = :pUsuario and u.senha = :pSenha");
		query.setParameter("pUsuario", usuario.getUsuario());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			usuarioLogado = (Usuario) query.getSingleResult();
			return usuarioLogado;
		}catch (Exception e) {
			return null;
		}
	}
	
	
	@Override
	public boolean adicionaItemCarrinho(ItemCarrinho item) {
		checkSession();
		for(ItemCarrinho itemCarrinho: carrinho) {
			if(itemCarrinho.getProduto().equals(item.getProduto())) {
				
				if(item.getQuantidade() + itemCarrinho.getQuantidade() > item.getProduto().getQuantidadeEstoque()) {
					return false;
				}
				
				itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + item.getQuantidade());
				return true;
			}
		}
		carrinho.add(item);
		return true;
		
	}

	@Override
	public boolean removeItemCarrinho(ItemCarrinho item) {
		checkSession();
		ItemCarrinho car = null;
		
		for (ItemCarrinho itemCarrinho : carrinho) {
			if (itemCarrinho.getProduto().getId().equals(item.getProduto().getId()) ) {
				car = itemCarrinho;
				break;
			}
		}
		
		if (car != null) {
			return carrinho.remove(car);
		}
		return false;
	}

	@Override
	public boolean alteraItemCarrinho(ItemCarrinho item) {
		checkSession();

		if(carrinho.contains(item) && item.getProduto().getQuantidadeEstoque() > item.getQuantidade()) {
			carrinho.remove(item);
			carrinho.add(item);
			return true;
		}
		return false;
	}

	@Override
	public List<ItemCarrinho> listaItensCarrinho() {
		checkSession();
		return carrinho;
	}
	
	@Override
	public boolean validaCartao(String cardNumber) {
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

	@Override
	public boolean finalizaCompra(String cardNumber) {
		checkSession();
		
		if (!validaCartao(cardNumber)) {
			return false;
		}
		
		//Verifica se todos os itens tem a quantidade necessária em estoque
		for(ItemCarrinho item: carrinho) {
			Produto produto = buscaProdutoPorId(item.getProduto().getId());
			if(produto.getQuantidadeEstoque() < item.getQuantidade()) {
				return false;
			}
		}
		
		//Subtrai cada item no carrinho do estoque
		for(ItemCarrinho item: carrinho) {
			Produto produto = buscaProdutoPorId(item.getProduto().getId());
			Integer quantidadeEmEstoque = produto.getQuantidadeEstoque();
			produto.setQuantidadeEstoque(quantidadeEmEstoque - item.getQuantidade());
			em.merge(produto);
		}
		
		RegistroVenda registro = new RegistroVenda();
		registro.setUsuario(usuarioLogado);
		registro.setItens(carrinho);
		
		em.persist(registro);
		
		carrinho.clear();
		return true;
	}

	@Override
	public List<Produto> listaProdutos() {
		checkSession();
		try {
			CriteriaQuery<Produto> query = em.getCriteriaBuilder().createQuery(Produto.class);
			query.select(query.from(Produto.class));
			
			List<Produto> list = em.createQuery(query).getResultList();
			return list;
		}catch (Exception e) {
			return null;
		}
	}
	
	
	private void checkSession() {
		if (usuarioLogado == null) {
			throw new NotAuthorizedException("Você deve se autenticar primeiro.");
		}
	}
	
	@Override
	public Produto buscaProdutoPorId(Long id) {
		return em.find(Produto.class, id);
	}
}
