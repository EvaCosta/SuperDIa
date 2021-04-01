package br.com.superdia.sessionbeans;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.superdia.dto.ProdutosExternos;
import br.com.superdia.modelo.Produto;

@Stateless
@Remote(IProduto.class)
public class ProdutoBean implements IProduto, IProdutoExterno{
	
	@PersistenceContext(unitName = "SuperDia")
	EntityManager em;

	@Override
	public boolean adiciona(Produto produto) {
		try {

			em.persist(produto);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Produto produto) {
		try {
			em.remove(em.merge(produto));
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean altera(Produto produto) {
		em.merge(produto);
		return true;
	}

	@Override
	public List<Produto> lista() {
		try {
			CriteriaQuery<Produto> query = em.getCriteriaBuilder().createQuery(Produto.class);
			query.select(query.from(Produto.class));
			
			List<Produto> list = em.createQuery(query).getResultList();
			return list;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public Produto buscaPorId(Long id) {
		return em.find(Produto.class, id);
	}

	@Override
	public boolean importarProdutosExternos(Loja loja) {
		

		URL url = new URL(loja.getLink());
		InputStream in = url.openStream();
	
		String json = new String(in.readAllBytes(), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();

		ProdutosExternos produtosExternos =
				mapper.readValue(json, ProdutosExternos.class);
		
		
		List<Produto> produtos = produtosExternos.products.stream().map(p -> new Produto(p.title,
				p.product_type, p.variants.get(0).price, loja.getId(), p.id)).collect(Collectors.toList());
		
		for (Produto produto : produtos) {
			
			try {
			  TypedQuery<Produto> query = em.createQuery("from Produto WHERE lojaId=? and idExterno=?", Produto.class);
			  query.setParameter(1, produto.getIdExterno());
			  query.setParameter(2, produto.getIdExterno());
			  query.getSingleResult();
			}catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
			adiciona(produto);
		}
	}
	
}
