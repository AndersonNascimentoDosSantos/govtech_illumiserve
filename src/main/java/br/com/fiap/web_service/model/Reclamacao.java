package br.com.fiap.web_service.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

import br.com.fiap.web_service.Enums.StatusReclamacao;

@Entity
@Table(name = "tbl_reclamacao")
public class Reclamacao {

	@Id
	@SequenceGenerator(name = "reclamacao", sequenceName = "seq_reclamacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reclamacao")
	@Column(name = "id_reclamacao")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column(name = "dt_data_criacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Column(name = "ds_latitude")
	private double latitude;

	@Column(name = "ds_longitude")
	private double longitude;

	@Column(name = "ds_descricao")
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "ds_status", nullable = false)
	private StatusReclamacao status;

	@PrePersist
	protected void onCreate() {
		this.dataCriacao = new Date();
	}

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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusReclamacao getStatus() {
		return status;
	}

	public void setStatus(StatusReclamacao status) {
		this.status = status;
	}

	// constructors, getters, and setters
}
