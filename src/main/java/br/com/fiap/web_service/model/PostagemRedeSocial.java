package br.com.fiap.web_service.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tbl_postagem_rede_social")
public class PostagemRedeSocial {
	@Id
	@SequenceGenerator(name = "post_rede", sequenceName = "seq_post_rede", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_rede")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_rede_social", nullable = false)
	private RedeSocial redeSocial;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@Column(name = "ds_messagem", nullable = false, columnDefinition = "CLOB")
	private String mensagem;

	@Column(name = "dt_data_postagem", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPostagem;

	public PostagemRedeSocial() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PrePersist
	protected void onCreate() {
		this.dataPostagem = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RedeSocial getRedeSocial() {
		return redeSocial;
	}

	public void setRedeSocial(RedeSocial redeSocial) {
		this.redeSocial = redeSocial;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

}
