package br.com.superdia.sessionbeans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.NotAuthorizedException;

import br.com.superdia.modelo.PerfilUsuario;
import br.com.superdia.modelo.Produto;
import br.com.superdia.modelo.RegistroVenda;
import br.com.superdia.modelo.Usuario;

@Stateful
@Remote(IServicosAdmin.class)
public class ServicosAdminBean implements IServicosAdmin {
	@PersistenceContext(unitName = "SuperDia")
	EntityManager em;
	
	private Usuario usuarioLogado;
	
	public ServicosAdminBean() {
		
	}
	
	@Override
	public Usuario autentica(Usuario usuario) {
		Query query = em.createQuery("from Usuario u where u.usuario = :pUsuario and u.senha = :pSenha and u.perfil = :pPerfil");
		query.setParameter("pUsuario", usuario.getUsuario());
		query.setParameter("pSenha", usuario.getSenha());
		query.setParameter("pPerfil", PerfilUsuario.ADMIN);
		try {
			usuarioLogado = (Usuario) query.getSingleResult();
			return usuarioLogado;
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean adicionaProduto(Produto produto) {
		checkSession();
		
		try {
			em.persist(produto);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean removeProduto(Produto produto) {
		checkSession();
		try {
			em.remove(em.merge(produto));
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean alteraProduto(Produto produto) {
		checkSession();
		em.merge(produto);
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
	
	@Override
	public boolean adicionaUsuario(Usuario usuario) {
		checkSession();
		try {
			em.persist(usuario);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeUsuario(Usuario usuario) {
		checkSession();
		try {
			em.remove(em.merge(usuario));
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean alteraUsuario(Usuario usuario) {
		checkSession();
		try {
			em.merge(usuario);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Usuario> listaUsuarios() {
		checkSession();
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
	public boolean removeVenda(RegistroVenda registroVenda) {
		try {
			em.remove(em.merge(registroVenda));
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<RegistroVenda> listaVendas() {
		try {
			CriteriaQuery<RegistroVenda> query = em.getCriteriaBuilder().createQuery(RegistroVenda.class);
			query.select(query.from(RegistroVenda.class));
			
			List<RegistroVenda> list = em.createQuery(query).getResultList();
			return list;
		}catch (Exception e) {
			return null;
		}
	}
	
	private void checkSession() {
		/*if (usuarioLogado == null) {
			throw new NotAuthorizedException("Você deve se autenticar primeiro.");
		}*/
	}
}
