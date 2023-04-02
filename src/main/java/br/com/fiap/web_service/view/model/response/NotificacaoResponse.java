package br.com.fiap.web_service.view.model.response;

import java.util.Date;

public class NotificacaoResponse {

  private Long idNotificacao;

  private UsuarioResponse usuario;

  private String mensagem;

  private Date dataEnvio;

  public Long getIdNotificacao() {
    return idNotificacao;
  }

  public void setIdNotificacao(Long idNotificacao) {
    this.idNotificacao = idNotificacao;
  }

  public UsuarioResponse getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioResponse usuario) {
    this.usuario = usuario;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public Date getDataEnvio() {
    return dataEnvio;
  }

  public void setDataEnvio(Date dataEnvio) {
    this.dataEnvio = dataEnvio;
  }

}
