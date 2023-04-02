package br.com.fiap.web_service.view.model.response;

import java.util.Date;

public class PostagemRedeSocialResponse {

  private Long id;

  private RedeSocialResponse redeSocial;

  private UsuarioResponse usuario;

  private String mensagem;

  private Date dataPostagem;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RedeSocialResponse getRedeSocial() {
    return redeSocial;
  }

  public void setRedeSocial(RedeSocialResponse redeSocial) {
    this.redeSocial = redeSocial;
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

  public Date getDataPostagem() {
    return dataPostagem;
  }

  public void setDataPostagem(Date dataPostagem) {
    this.dataPostagem = dataPostagem;
  }

}
