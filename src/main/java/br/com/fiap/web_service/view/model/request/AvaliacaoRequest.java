package br.com.fiap.web_service.view.model.request;

import java.util.Date;

public class AvaliacaoRequest {
  // #region atributes

  private UsuarioRequest usuario;

  private EmpresaRequest empresa;
  // private Long usuarioId;
  // private Long empresaId;
  private Integer nota;

  private String comentario;

  private Date dataAvaliacao;

  // #endregion
  // #region getters and setters

  public UsuarioRequest getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioRequest usuario) {
    this.usuario = usuario;
  }

  public EmpresaRequest getEmpresa() {
    return empresa;
  }

  public void setEmpresa(EmpresaRequest empresaDto) {
    this.empresa = empresaDto;
  }

  public Integer getNota() {
    return nota;
  }

  // public Long getUsuarioId() {
  // return usuarioId;
  // }

  // public void setUsuarioId(Long usuarioId) {
  // this.usuarioId = usuarioId;
  // }

  // public Long getEmpresaId() {
  // return empresaId;
  // }

  // public void setEmpresaId(Long empresaId) {
  // this.empresaId = empresaId;
  // }

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
  // #endregion
}
