package br.com.fiap.web_service.view.model.response;

import java.util.Date;

public class MensagemForumResponse {

  private Long idMensagem;

  private TopicoForumResponse topico;

  private UsuarioResponse usuario;

  private String conteudo;

  private Date dataPostagem;

  // getters and setters

  public Long getIdMensagem() {
    return idMensagem;
  }

  public void setIdMensagem(Long idMensagem) {
    this.idMensagem = idMensagem;
  }

  public TopicoForumResponse getTopico() {
    return topico;
  }

  public void setTopico(TopicoForumResponse topico) {
    this.topico = topico;
  }

  public UsuarioResponse getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioResponse usuario) {
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
