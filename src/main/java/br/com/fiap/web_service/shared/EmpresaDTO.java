package br.com.fiap.web_service.shared;

import java.util.List;

public class EmpresaDTO {

  private Long idEmpresa;

  private String nome;

  private String cnpj;

  private String email;

  private List<AvaliacaoDTO> avaliacoes;

  private List<ReclamacaoDTO> reclamacoes;

  private List<MensagemChatDTO> mensagemChats;

  // getters and setters

  public List<MensagemChatDTO> getMensagemChats() {
    return mensagemChats;
  }

  public void setMensagemChats(List<MensagemChatDTO> mensagemChats) {
    this.mensagemChats = mensagemChats;
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
