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
@Table(name = "tbl_avaliacao")

public class Avaliacao {

	@Id
	@SequenceGenerator(name = "avaliacao", sequenceName = "seq_avaliacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avaliacao")
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "id_usuario", nullable = false)

	@JsonBackReference("usuario_avaliacoes")
	private Usuario usuario;

	@ManyToOne()
	@JoinColumn(name = "id_empresa_eletrica", nullable = false)
	@JsonBackReference("empresa_avaliacoes")
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "ds_nota", nullable = false)
	private Integer nota;

	// @Type(value = materializedClob)
	@Column(name = "ds_comentario", nullable = false, columnDefinition = "CLOB")
	@Lob
	private String comentario;

	@Column(name = "dt_data_avaliacao", updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date dataAvaliacao;

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

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

}
