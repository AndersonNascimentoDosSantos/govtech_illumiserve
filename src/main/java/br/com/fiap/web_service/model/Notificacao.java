package br.com.fiap.web_service.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_notificacao")
public class Notificacao {

	@Id
	@SequenceGenerator(name = "notification", sequenceName = "seq_notificacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification")
	@Column(name = "id_notificacao")
	private Long idNotificacao;

	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_usuario")
	@JsonBackReference("usuario_notificacao")
	private Usuario usuario;

	@Column(name = "ds_mensagem", columnDefinition = "CLOB")
	@Lob
	private String mensagem;

	@Column(name = "dt_data_envio", updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

	private Date dataEnvio;

	public Long getIdNotificacao() {
		return idNotificacao;
	}

	// public Notificacao() {
	// super();
	// // TODO Auto-generated constructor stub
	// }

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
