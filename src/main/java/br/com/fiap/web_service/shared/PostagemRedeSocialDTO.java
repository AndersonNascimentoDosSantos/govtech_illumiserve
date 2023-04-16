package br.com.fiap.web_service.shared;

import java.util.Date;

public class PostagemRedeSocialDTO {

  private Long id;

  private RedeSocialDTO redeSocial;

  private UsuarioDTO usuario;

  private ReclamacaoDTO reclamacao;

  private String mensagem;

  private Date dataPostagem;

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

  public RedeSocialDTO getRedeSocial() {
    return redeSocial;
  }

  public void setRedeSocial(RedeSocialDTO redeSocial) {
    this.redeSocial = redeSocial;
  }

  public UsuarioDTO getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioDTO usuario) {
    this.usuario = usuario;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public Date getDataPostagem() {
    return dataPostagem;
  }

  public void setDataPostagem(Date dataPostagem) {
    this.dataPostagem = dataPostagem;
  }

}
