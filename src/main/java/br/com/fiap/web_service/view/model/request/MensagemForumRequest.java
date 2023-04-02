package br.com.fiap.web_service.view.model.request;

import java.util.Date;

public class MensagemForumRequest {

  private TopicoForumRequest topico;

  private UsuarioRequest usuario;

  private String conteudo;

  private Date dataPostagem;

  // getters and setters

  public TopicoForumRequest getTopico() {
    return topico;
  }

  public void setTopico(TopicoForumRequest topico) {
    this.topico = topico;
  }

  public UsuarioRequest getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioRequest usuario) {
    this.usuario = usuario;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public Date getDataPostagem() {
    return dataPostagem;
  }

  public void setDataPostagem(Date dataPostagem) {
    this.dataPostagem = dataPostagem;
  }

}
