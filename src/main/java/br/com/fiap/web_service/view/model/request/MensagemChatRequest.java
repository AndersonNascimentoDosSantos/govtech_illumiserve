package br.com.fiap.web_service.view.model.request;

import java.util.Date;

public class MensagemChatRequest {

  private UsuarioRequest remetente;

  private EmpresaRequest destinatario;

  private String conteudo;

  private Date dataEnvio;

  public UsuarioRequest getRemetente() {
    return remetente;
  }

  public void setRemetente(UsuarioRequest remetente) {
    this.remetente = remetente;
  }

  public EmpresaRequest getDestinatario() {
    return destinatario;
  }

  public void setDestinatario(EmpresaRequest destinatario) {
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
