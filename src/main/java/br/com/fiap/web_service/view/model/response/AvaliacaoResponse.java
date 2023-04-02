package br.com.fiap.web_service.view.model.response;

import java.util.Date;

public class AvaliacaoResponse {
  // #region atributes
  private Long id;

  private UsuarioResponse usuario;

  private EmpresaResponse empresa;

  private Integer nota;

  private String comentario;

  private Date dataAvaliacao;

  // #endregion
  // #region getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UsuarioResponse getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioResponse usuario) {
    this.usuario = usuario;
  }

  public EmpresaResponse getEmpresa() {
    return empresa;
  }

  public void setEmpresa(EmpresaResponse empresaDto) {
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
