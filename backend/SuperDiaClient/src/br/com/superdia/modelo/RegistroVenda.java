package br.com.superdia.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class RegistroVenda implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5934018869663621595L;

	@Id
	@SequenceGenerator(name = "registro_venda_id",
			sequenceName = "registro_venda_seq",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "registro_venda_id")
	private Long id;
	
	@OneToOne
	private Usuario usuario;

	@OneToMany
	private List<ItemCarrinho> itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	
	
}
