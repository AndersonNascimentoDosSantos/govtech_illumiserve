package br.com.fiap.web_service.shared;

import java.util.Date;
import java.util.List;

import br.com.fiap.web_service.Enums.StatusReclamacao;

public class ReclamacaoDTO {

  private Long id;

  private UsuarioDTO usuario;

  private EmpresaDTO empresa;

  private List<PostagemRedeSocialDTO> redeSocial;

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

  public List<PostagemRedeSocialDTO> getRedeSocial() {
    return redeSocial;
  }

  public void setRedeSocial(List<PostagemRedeSocialDTO> redeSocial) {
    this.redeSocial = redeSocial;
  }

}
