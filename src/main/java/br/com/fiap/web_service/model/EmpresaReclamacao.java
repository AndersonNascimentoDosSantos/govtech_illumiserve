package br.com.fiap.web_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_empresa_reclamacao")
public class EmpresaReclamacao {

	@Id
	@SequenceGenerator(name = "emp_reclamacao", sequenceName = "seq_emp_reclamacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_reclamacao")
	@Column(name = "id_empresa_reclamacao")
	private Long idEmpresaReclamacao;

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_reclamacao")
	private Reclamacao reclamacao;

	// public EmpresaReclamacao() {
	// super();
	// // TODO Auto-generated constructor stub
	// }

	public Long getIdEmpresaReclamacao() {
		return idEmpresaReclamacao;
	}

	public void setIdEmpresaReclamacao(Long idEmpresaReclamacao) {
		this.idEmpresaReclamacao = idEmpresaReclamacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Reclamacao getReclamacao() {
		return reclamacao;
	}

	public void setReclamacao(Reclamacao reclamacao) {
		this.reclamacao = reclamacao;
	}

}
