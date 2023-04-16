package br.com.fiap.web_service.shared;

import java.util.Date;

public class MensagemForumDTO {

  private Long idMensagem;

  private TopicoForumDTO topico;

  private UsuarioDTO usuario;

  private String conteudo;

  private Date dataPostagem;

  public Long getIdMensagem() {
    return idMensagem;
  }

  public void setIdMensagem(Long idMensagem) {
    this.idMensagem = idMensagem;
  }

  public TopicoForumDTO getTopico() {
    return topico;
  }

  public void setTopico(TopicoForumDTO topico) {
    this.topico = topico;
  }

  public UsuarioDTO getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioDTO usuario) {
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
