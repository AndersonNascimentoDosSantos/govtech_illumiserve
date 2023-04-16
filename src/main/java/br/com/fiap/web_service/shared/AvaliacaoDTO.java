package br.com.fiap.web_service.shared;

import java.util.Date;

public class AvaliacaoDTO {

  private Long id;

  private UsuarioDTO usuario;

  private EmpresaDTO empresa;

  private Integer nota;

  private String comentario;

  private Date dataAvaliacao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UsuarioDTO getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioDTO usuario) {
    this.usuario = usuario;
  }

  public EmpresaDTO getEmpresa() {
    return empresa;
  }

  public void setEmpresa(EmpresaDTO empresa) {
    this.empresa = empresa;
  }

  public Integer getNota() {
    return nota;
  }

  public void setNota(Integer nota) {
    this.nota = nota;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  public Date getDataAvaliacao() {
    return dataAvaliacao;
  }

  public void setDataAvaliacao(Date dataAvaliacao) {
    this.dataAvaliacao = dataAvaliacao;
  }

}
