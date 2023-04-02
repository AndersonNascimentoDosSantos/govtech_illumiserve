package br.com.fiap.web_service.shared;

import java.util.Date;

public class TopicoForumDTO {

  private Long idTopico;

  private String titulo;

  private String descricao;

  private Date dataCriacao;

  private UsuarioDTO usuarioCriador;

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

  public UsuarioDTO getUsuarioCriador() {
    return usuarioCriador;
  }

  public void setUsuarioCriador(UsuarioDTO usuarioCriador) {
    this.usuarioCriador = usuarioCriador;
  }

}
