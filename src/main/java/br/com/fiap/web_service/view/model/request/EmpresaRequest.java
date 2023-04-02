package br.com.fiap.web_service.view.model.request;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class EmpresaRequest {

  private String nome;

  private String cnpj;

  private String email;

  private String senha;

  private List<AvaliacaoRequest> avaliacoes;

  private List<ReclamacaoRequest> reclamacoes;

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

  public List<AvaliacaoRequest> getAvaliacoes() {
    return avaliacoes;
  }

  public void setAvaliacoes(List<AvaliacaoRequest> avaliacoes) {
    this.avaliacoes = avaliacoes;
  }

  public List<ReclamacaoRequest> getReclamacoes() {
    return reclamacoes;
  }

  public void setReclamacoes(List<ReclamacaoRequest> reclamacoes) {
    this.reclamacoes = reclamacoes;
  }

}
