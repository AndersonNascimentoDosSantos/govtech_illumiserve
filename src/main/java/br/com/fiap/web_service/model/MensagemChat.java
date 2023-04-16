package br.com.fiap.web_service.model;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "tbl_mensagem_chat")
public class MensagemChat {
	@Id
	@SequenceGenerator(name = "msg_seq", sequenceName = "seq_msg", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
	private Long id;

	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_remetente", nullable = false)
	@JsonBackReference("usuario_mensagemChats")
	private Usuario remetente;

	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_destinatario", nullable = false)
	@JsonBackReference("empresa_mensagemChats")
	private Empresa destinatario;

	@Column(name = "ds_conteudo", nullable = false, columnDefinition = "CLOB")
	@Lob
	private String conteudo;

	@Column(name = "dt_data_envio", updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date dataEnvio;

	@Column(name = "dt_data_update", nullable = true)
	@UpdateTimestamp
	private Date dataUpdate;
	// public MensagemChat() {
	// super();
	// // TODO Auto-generated constructor stub
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public Empresa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Empresa destinatario) {
		this.destinatario = destinatario;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	// constructors, getters, setters
}
