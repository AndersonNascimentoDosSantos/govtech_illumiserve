package br.com.fiap.web_service.shared;

import java.util.Date;

public class MensagemChatDTO {

  private Long id;

  private UsuarioDTO remetente;

  private EmpresaDTO destinatario;

  private String conteudo;

  private Date dataEnvio;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UsuarioDTO getRemetente() {
    return remetente;
  }

  public void setRemetente(UsuarioDTO remetente) {
    this.remetente = remetente;
  }

  public EmpresaDTO getDestinatario() {
    return destinatario;
  }

  public void setDestinatario(EmpresaDTO destinatario) {
    this.destinatario = destinatario;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public Date getDataEnvio() {
    return dataEnvio;
  }

  public void setDataEnvio(Date dataEnvio) {
    this.dataEnvio = dataEnvio;
  }

}
