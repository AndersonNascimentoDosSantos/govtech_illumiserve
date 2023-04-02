package br.com.fiap.web_service.view.model.request;

import java.util.Date;

public class PostagemRedeSocialRequest {

  private RedeSocialRequest redeSocial;

  private UsuarioRequest usuario;

  private String mensagem;

  private Date dataPostagem;

  public RedeSocialRequest getRedeSocial() {
    return redeSocial;
  }

  public void setRedeSocial(RedeSocialRequest redeSocial) {
    this.redeSocial = redeSocial;
  }

  public UsuarioRequest getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioRequest usuario) {
    this.usuario = usuario;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public Date getDataPostagem() {
    return dataPostagem;
  }

  public void setDataPostagem(Date dataPostagem) {
    this.dataPostagem = dataPostagem;
  }

}
