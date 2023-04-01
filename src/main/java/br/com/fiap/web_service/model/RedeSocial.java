package br.com.fiap.web_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_rede_social")
public class RedeSocial {
	@Id
	@SequenceGenerator(name = "rede_social", sequenceName = "seq_rede_social", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rede_social")
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "urlPagina", nullable = false)
	private String urlPagina;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrlPagina() {
		return urlPagina;
	}

	public void setUrlPagina(String urlPagina) {
		this.urlPagina = urlPagina;
	}

	// constructors, getters, setters
}
