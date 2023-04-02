package br.com.fiap.web_service.view.model.response;

import java.util.Date;

public class EmpresaChatResponse {

  private Long id;

  private UsuarioResponse remetente;

  private EmpresaResponse destinatario;

  private String conteudo;

  private Date dataEnvio;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UsuarioResponse getRemetente() {
    return remetente;
  }

  public void setRemetente(UsuarioResponse remetente) {
    this.remetente = remetente;
  }

  public EmpresaResponse getDestinatario() {
    return destinatario;
  }

  public void setDestinatario(EmpresaResponse destinatario) {
    this.destinatario = destinatario;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public Date getDataEnvio() {
    return dataEnvio;
  }

  public void setDataEnvio(Date dataEnvio) {
    this.dataEnvio = dataEnvio;
  }

}
