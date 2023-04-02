package br.com.fiap.web_service.view.model.response;

import java.util.Date;

import br.com.fiap.web_service.Enums.StatusReclamacao;

public class ReclamacaoResponse {

  private Long id;

  private UsuarioResponse usuario;

  private EmpresaResponse empresa;

  private Date dataCriacao;

  private double latitude;

  private double longitude;

  private String descricao;

  private StatusReclamacao status;

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

  public void setEmpresa(EmpresaResponse empresa) {
    this.empresa = empresa;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public StatusReclamacao getStatus() {
    return status;
  }

  public void setStatus(StatusReclamacao status) {
    this.status = status;
  }

  // constructors, getters, and setters

}
