package br.com.fiap.web_service.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_rede_social")
public class RedeSocial {
	@Id
	@SequenceGenerator(name = "rede_social", sequenceName = "seq_rede_social", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rede_social")
	@Column(name = "idRedeSocial")
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "urlPagina", nullable = false)
	private String urlPagina;

	// #verified
	@OneToMany(mappedBy = "redeSocial")
	@JsonManagedReference("posts_redeSocial")
	private List<PostagemRedeSocial> postRedeSocial;

	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_usuario", nullable = false)
	@JsonBackReference("usuario_redeSocial")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrlPagina() {
		return urlPagina;
	}

	public void setUrlPagina(String urlPagina) {
		this.urlPagina = urlPagina;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PostagemRedeSocial> getPostRedeSocial() {
		return postRedeSocial;
	}

	public void setPostRedeSocial(List<PostagemRedeSocial> postRedeSocial) {
		this.postRedeSocial = postRedeSocial;
	}

}
