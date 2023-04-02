package br.com.fiap.web_service.view.model.response;

import java.util.Date;

public class TopicoForumResponse {

  private Long idTopico;

  private String titulo;

  private String descricao;

  private Date dataCriacao;

  private UsuarioResponse usuarioCriador;

  public Long getIdTopico() {
    return idTopico;
  }

  public void setIdTopico(Long idTopico) {
    this.idTopico = idTopico;
  }

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

  public UsuarioResponse getUsuarioCriador() {
    return usuarioCriador;
  }

  public void setUsuarioCriador(UsuarioResponse usuarioCriador) {
    this.usuarioCriador = usuarioCriador;
  }

}
