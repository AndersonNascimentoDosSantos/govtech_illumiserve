package br.com.fiap.web_service.model;

import java.util.Date;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_topico_forum")
public class TopicoForum {

	@Id
	@SequenceGenerator(name = "topico", sequenceName = "seq_topico_forum", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topico")
	@Column(name = "id_topico")
	private Long idTopico;
	@Column(name = "ds_titulo", nullable = false)
	private String titulo;
	@Column(name = "dt_descricao", nullable = false, columnDefinition = "CLOB")
	@Lob
	private String descricao;

	@Column(name = "dt_data_criacao", updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date dataCriacao;

	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_usuario", nullable = false)
	@JsonBackReference("usuario_topicoForum")
	private Usuario usuarioCriador;

	// #verified
	@OneToMany(mappedBy = "topico")
	@JsonManagedReference("topico_mensagemForum")
	private List<MensagemForum> mensagemForum;

	public Long getIdTopico() {
		return idTopico;
	}

	public void setIdTopico(Long idTopico) {
		this.idTopico = idTopico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}

	public List<MensagemForum> getMensagemForum() {
		return mensagemForum;
	}

	public void setMensagemForum(List<MensagemForum> mensagemForum) {
		this.mensagemForum = mensagemForum;
	}

}
