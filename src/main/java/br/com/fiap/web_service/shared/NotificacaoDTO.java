package br.com.fiap.web_service.shared;

import java.util.Date;

public class NotificacaoDTO {

  private Long idNotificacao;

  private UsuarioDTO usuario;

  private String mensagem;

  private Date dataEnvio;

  public Long getIdNotificacao() {
    return idNotificacao;
  }

  public void setIdNotificacao(Long idNotificacao) {
    this.idNotificacao = idNotificacao;
  }

  public UsuarioDTO getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioDTO usuario) {
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
