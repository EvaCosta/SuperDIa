package br.com.superdia.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.superdia.modelo.RegistroVenda;
import br.com.superdia.modelo.Usuario;

@Stateless
@Remote(IRegistroVenda.class)
public class RegistroVendaBean implements IRegistroVenda{
	

	@PersistenceContext(unitName = "SuperDia")
	EntityManager em;

	@Override
	public boolean adiciona(RegistroVenda registro) {
		try {
			em.persist(registro);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(RegistroVenda registro) {
		try {
			em.remove(em.merge(registro));
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean altera(RegistroVenda registro) {
		try {
			em.merge(registro);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<RegistroVenda> lista() {
		try {
			CriteriaQuery<RegistroVenda> query = em.getCriteriaBuilder().createQuery(RegistroVenda.class);
			query.select(query.from(RegistroVenda.class));
			
			List<RegistroVenda> list = em.createQuery(query).getResultList();
			return list;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<RegistroVenda> listaPorUsuario(Usuario usuario) {
		return null;
	}

}
