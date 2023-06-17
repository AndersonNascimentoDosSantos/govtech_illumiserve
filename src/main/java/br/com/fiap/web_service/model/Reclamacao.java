package br.com.fiap.web_service.model;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import br.com.fiap.web_service.Enums.StatusReclamacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_reclamacao")
public class Reclamacao {

	@Id
	@SequenceGenerator(name = "reclamacao", sequenceName = "seq_reclamacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reclamacao")
	@Column(name = "id_reclamacao")
	private Long id;

	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_usuario")
	@JsonBackReference("usuario_reclamacoes")
	private Usuario usuario;

	// #verified
	@ManyToOne()
	@JoinColumn(name = "id_empresa")
	@JsonBackReference("empresa_reclamacoes")
	private Empresa empresa;

	// #verified
	@ManyToMany(mappedBy = "reclamacao")
	@JsonManagedReference("reclamacao_redeSocial")
	private List<PostagemRedeSocial> postredeSocial;

	@Column(name = "dt_data_criacao", updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
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

	public List<PostagemRedeSocial> getPostredeSocial() {
		return postredeSocial;
	}

	public void setPostredeSocial(List<PostagemRedeSocial> postredeSocial) {
		this.postredeSocial = postredeSocial;
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

}
