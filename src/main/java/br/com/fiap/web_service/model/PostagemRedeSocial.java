package br.com.fiap.web_service.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Column(name = "idPostagemRedeSocial")
	private Long id;

	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_rede_social", nullable = false)
	@JsonBackReference("posts_redeSocial")
	private RedeSocial redeSocial;

	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_reclamacao", nullable = false)
	@JsonBackReference("reclamacao_redeSocial")
	private Reclamacao reclamacao;

	@Column(name = "ds_messagem", nullable = false, columnDefinition = "text")
	private String mensagem;

	@Column(name = "dt_data_postagem", updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPostagem;

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

	public Reclamacao getReclamacao() {
		return reclamacao;
	}

	public void setReclamacao(Reclamacao reclamacao) {
		this.reclamacao = reclamacao;
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
