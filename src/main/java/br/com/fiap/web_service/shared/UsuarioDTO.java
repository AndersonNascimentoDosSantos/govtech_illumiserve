package br.com.fiap.web_service.shared;

import java.io.Serializable;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import br.com.fiap.web_service.Enums.TipoUsuario;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

public class UsuarioDTO {

  // public static Usuario autenticar(String email, String password, EntityManager
  // em) throws Exception {
  // try {
  // Usuario usuario = em.createNamedQuery("Usuario.findByEmail", Usuario.class)
  // .setParameter("email", email)
  // .getSingleResult();
  // if (usuario.verificaSenha(password)) {
  // return usuario; // authentication successful
  // } else {
  // throw new Exception("Invalid password for user " + email);
  // }
  // } catch (NoResultException e) {
  // throw new Exception("User " + email + " not found");
  // }
  // }

  private Long idUsuario;

  private String nome;

  private String email;

  private String senha;

  private TipoUsuario tipoUsuario;

  // #reclamacoes

  private List<ReclamacaoDTO> reclamacoes;

  // #avaliacoes

  private List<AvaliacaoDTO> avaliacoes;

  // #topicos forum

  private List<TopicoForumDTO> topicoForum;

  // #mensagem forum

  // #rede sociais

  private List<RedeSocialDTO> redeSocial;

  // #menssagens do chat

  private List<MensagemChatDTO> mensagemChats;

  // #notificação

  private List<NotificacaoDTO> notificação;

  public String getSenha() {
    return senha;
  }

  public List<TopicoForumDTO> getTopicoForum() {
    return topicoForum;
  }

  public void setTopicoForum(List<TopicoForumDTO> topicoForum) {
    this.topicoForum = topicoForum;
  }

  public List<RedeSocialDTO> getRedeSocial() {
    return redeSocial;
  }

  public void setRedeSocial(List<RedeSocialDTO> redeSocial) {
    this.redeSocial = redeSocial;
  }

  public List<MensagemChatDTO> getMensagemChats() {
    return mensagemChats;
  }

  public void setMensagemChats(List<MensagemChatDTO> mensagemChats) {
    this.mensagemChats = mensagemChats;
  }

  public List<NotificacaoDTO> getNotificação() {
    return notificação;
  }

  public void setNotificação(List<NotificacaoDTO> notificação) {
    this.notificação = notificação;
  }

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public TipoUsuario getTipoUsuario() {
    return tipoUsuario;
  }

  public void setTipoUsuario(TipoUsuario tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
  }

  public List<ReclamacaoDTO> getReclamacoes() {
    return this.reclamacoes;
  }

  public void setReclamacoes(List<ReclamacaoDTO> reclamacoes) {
    this.reclamacoes = reclamacoes;
  }

  public List<AvaliacaoDTO> getAvaliacoes() {
    return this.avaliacoes;
  }

  public void setAvaliacoes(List<AvaliacaoDTO> avaliacoes) {
    this.avaliacoes = avaliacoes;
  }

  public void setSenha(String senha) {
    String hash = BCrypt.hashpw(senha, BCrypt.gensalt());
    this.senha = hash;
  }

  public boolean verificaSenha(String senha) {
    return BCrypt.checkpw(senha, this.senha);
  }
}
