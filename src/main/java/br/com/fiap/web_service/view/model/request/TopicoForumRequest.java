package br.com.fiap.web_service.view.model.request;

import java.util.Date;

public class TopicoForumRequest {

  private String titulo;

  private String descricao;

  private Date dataCriacao;

  private UsuarioRequest usuarioCriador;

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public UsuarioRequest getUsuarioCriador() {
    return usuarioCriador;
  }

  public void setUsuarioCriador(UsuarioRequest usuarioCriador) {
    this.usuarioCriador = usuarioCriador;
  }

}
