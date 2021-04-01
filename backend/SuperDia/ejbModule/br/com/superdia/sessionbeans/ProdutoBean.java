package br.com.superdia.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.superdia.modelo.Produto;

@Remote(IProduto.class)
public class ProdutoBean implements IProduto{
	
	@PersistenceContext(unitName = "SuperDia")
	EntityManager em;

	@Override
	public void adiciona(Produto produto) {
		em.persist(produto);
	}

	@Override
	public void remove(Produto produto) {
		em.remove(em.merge(produto));
	}

	@Override
	public void altera(Produto produto) {
		em.merge(produto);
	}

	@Override
	public List<Produto> getProdutos() {
		CriteriaQuery<Produto> query = em.getCriteriaBuilder().createQuery(Produto.class);
		query.select(query.from(Produto.class));
		
		List<Produto> list = em.createQuery(query).getResultList();
		return list;
	}
	
}
