package br.com.fiap.web_service.view.model.response;

public class RedeSocialResponse {

  private Long id;

  private String nome;

  private String urlPagina;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getUrlPagina() {
    return urlPagina;
  }

  public void setUrlPagina(String urlPagina) {
    this.urlPagina = urlPagina;
  }

}
