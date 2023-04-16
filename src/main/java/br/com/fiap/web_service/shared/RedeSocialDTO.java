package br.com.fiap.web_service.shared;

public class RedeSocialDTO {

  private Long id;

  private String nome;

  private String urlPagina;

  private UsuarioDTO usuario;

  private ReclamacaoDTO reclamacao;

  public UsuarioDTO getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioDTO usuario) {
    this.usuario = usuario;
  }

  public ReclamacaoDTO getReclamacao() {
    return reclamacao;
  }

  public void setReclamacao(ReclamacaoDTO reclamacao) {
    this.reclamacao = reclamacao;
  }

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
