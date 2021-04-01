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
	public boolean adiciona(Usuario usuario) {
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :pUsuario");
			q.setParameter("pUsuario", usuario.getUsuario());
			Usuario usuarioExistente = (Usuario) q.getSingleResult();
			if(usuarioExistente == null) {
				em.persist(usuario);
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			return false;
		}
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
	public List<Usuario> getUsuarios() {
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
		Query query = em.createQuery("from Usuario u where u.usuario = :pUsuario and u.senha = :pSenha");
		query.setParameter("pUsuario", usuario.getUsuario());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario usuarioLogado = (Usuario) query.getSingleResult();
			return usuarioLogado;
		}catch (Exception e) {
			return null;
		}
	}
	
}
