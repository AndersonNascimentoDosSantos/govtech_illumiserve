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
@Table(name = "tbl_notificacao")
public class Notificacao {

	@Id
	@SequenceGenerator(name = "notification", sequenceName = "seq_notificacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification")
	@Column(name = "id_notificacao")
	private Long idNotificacao;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "ds_mensagem", columnDefinition = "CLOB")
	private String mensagem;

	@Column(name = "dt_data_envio", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvio;

	@PrePersist
	protected void onCreate() {
		this.dataEnvio = new Date();
	}

	public Notificacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdNotificacao() {
		return idNotificacao;
	}

	public void setIdNotificacao(Long idNotificacao) {
		this.idNotificacao = idNotificacao;
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

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

}
