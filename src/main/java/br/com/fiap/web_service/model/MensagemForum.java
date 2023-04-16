package br.com.fiap.web_service.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tbl_mensagem_forum")
public class MensagemForum {

	@Id
	@SequenceGenerator(name = "forum", sequenceName = "seq_forum", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forum")
	@Column(name = "id_mensagem")
	private Long idMensagem;
	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_topico")
	@JsonBackReference("topico_mensagemForum")
	private TopicoForum topico;

	// #verified
	// @ManyToOne()
	// @JoinColumn(name = "id_usuario")
	// @JsonBackReference("usuario_mensagemForum")
	// private Usuario usuario;

	@Column(name = "ds_conteudo", columnDefinition = "CLOB")
	@Lob
	private String conteudo;

	@Column(name = "dt_data_postagem", updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date dataPostagem;

	public Long getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}

	public TopicoForum getTopico() {
		return topico;
	}

	public void setTopico(TopicoForum topico) {
		this.topico = topico;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	@PrePersist
	protected void onCreate() {
		this.dataPostagem = new Date();
	}
	// getters and setters
}
