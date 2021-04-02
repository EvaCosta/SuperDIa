package br.com.superdia.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.superdia.modelo.Usuario;

@Stateless
@Remote(IUsuario.class)
public class UsuarioBean implements IUsuario{

	@PersistenceContext(unitName = "SuperDia")
	EntityManager em;

	@Override
	public void adiciona(Usuario usuario) {
		em.persist(usuario);
	}

	@Override
	public boolean remove(Usuario usuario) {
		try {
			em.remove(em.merge(usuario));
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean altera(Usuario usuario) {
		try {
			em.merge(usuario);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Usuario> lista() {
		try {
			CriteriaQuery<Usuario> query = em.getCriteriaBuilder().createQuery(Usuario.class);
			query.select(query.from(Usuario.class));
			
			List<Usuario> list = em.createQuery(query).getResultList();
			return list;
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Usuario login(Usuario usuario) {
		CriteriaQuery<Usuario> query = em.getCriteriaBuilder().createQuery(Usuario.class);
		
		query.select(query.from(Usuario.class));
		
		List<Usuario> list = em.createQuery(query).getResultList();
		
		for (Usuario user : list) {
		
			if(usuario.getUsuario().equals(user.getUsuario()) && usuario.getSenha().equals(user.getSenha())) {
				return user;
			}
		}		
		return null;
	}
	
}
