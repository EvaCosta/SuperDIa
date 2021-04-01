package br.com.superdia.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.superdia.modelo.Produto;

@Stateless
@Remote(IProduto.class)
public class ProdutoBean implements IProduto{
	
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
	public List<Produto> getProdutos() {
		try {
			CriteriaQuery<Produto> query = em.getCriteriaBuilder().createQuery(Produto.class);
			query.select(query.from(Produto.class));
			
			List<Produto> list = em.createQuery(query).getResultList();
			return list;
		}catch (Exception e) {
			return null;
		}
	}
	
}
