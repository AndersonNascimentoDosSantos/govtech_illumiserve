package br.com.fiap.web_service.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "tbl_empresas_eletricas")
public class Empresa {

	@Id
	@SequenceGenerator(name = "empresa", sequenceName = "seq_empresa", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa")
	@Column(name = "id_empresa")
	private Long idEmpresa;

	public String getSenha() {
		return senha;
	}

	@Column(name = "nom_nome", nullable = false)
	private String nome;

	@Column(name = "ds_cnpj", nullable = false)
	private String cnpj;

	@Column(name = "ds_email", nullable = false, unique = true)
	private String email;

	@Column(name = "ds_senha", nullable = false)
	private String senha;

	@OneToMany(mappedBy = "empresa")
	private List<Avaliacao> avaliacoes;

	@OneToMany(mappedBy = "empresa")
	private List<Reclamacao> reclamacoes;

	// public Empresa() {
	// }

	// getters and setters

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

	public void setSenha(String senha) {
		String hash = BCrypt.hashpw(senha, BCrypt.gensalt());
		this.senha = hash;
	}

	public boolean verificaSenha(String senha) {
		return BCrypt.checkpw(senha, this.senha);
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<Reclamacao> getReclamacoes() {
		return reclamacoes;
	}

	public void setReclamacoes(List<Reclamacao> reclamacoes) {
		this.reclamacoes = reclamacoes;
	}

}
