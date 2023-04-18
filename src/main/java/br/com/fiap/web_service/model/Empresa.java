package br.com.fiap.web_service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_empresas_eletricas")
public class Empresa {

	@Id
	@SequenceGenerator(name = "empresa", sequenceName = "seq_empresa", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa")
	@Column(name = "id_empresa")
	private Long idEmpresa;

	@Column(name = "nom_nome", nullable = false)
	private String nome;

	@Column(name = "ds_cnpj", nullable = false, unique = true)
	private String cnpj;

	@Column(name = "ds_email", nullable = false, unique = true)
	private String email;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("empresa_avaliacoes")
	private List<Avaliacao> avaliacoes;

	@OneToMany(mappedBy = "empresa")
	@JsonManagedReference("empresa_reclamacoes")
	private List<Reclamacao> reclamacoes;

	@OneToMany(mappedBy = "destinatario")
	@JsonManagedReference("empresa_mensagemChats")
	private List<MensagemChat> mensagemChats;

	public Empresa() {
	}

	public List<MensagemChat> getMensagemChats() {
		return mensagemChats;
	}

	public void setMensagemChats(List<MensagemChat> mensagemChats) {
		this.mensagemChats = mensagemChats;
	}

	public List<Reclamacao> getReclamacoes() {
		return reclamacoes;
	}

	public void setReclamacoes(List<Reclamacao> reclamacoes) {
		this.reclamacoes = reclamacoes;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long id_empresa) {
		this.idEmpresa = id_empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
