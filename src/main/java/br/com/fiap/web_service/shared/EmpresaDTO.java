package br.com.fiap.web_service.shared;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class EmpresaDTO {

  private Long idEmpresa;

  private String nome;

  private String cnpj;

  private String email;

  private String senha;

  private List<AvaliacaoDTO> avaliacoes;

  private List<ReclamacaoDTO> reclamacoes;

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

  public List<AvaliacaoDTO> getAvaliacoes() {
    return avaliacoes;
  }

  public void setAvaliacoes(List<AvaliacaoDTO> avaliacoes) {
    this.avaliacoes = avaliacoes;
  }

  public List<ReclamacaoDTO> getReclamacoes() {
    return reclamacoes;
  }

  public void setReclamacoes(List<ReclamacaoDTO> reclamacoes) {
    this.reclamacoes = reclamacoes;
  }

}
