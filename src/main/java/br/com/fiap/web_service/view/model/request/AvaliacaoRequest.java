package br.com.fiap.web_service.view.model.request;

import java.util.Date;

public class AvaliacaoRequest {
  // #region atributes

  private UsuarioRequest usuario;

  private EmpresaRequest empresa;

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
