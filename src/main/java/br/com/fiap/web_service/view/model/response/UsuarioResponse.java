package br.com.fiap.web_service.view.model.response;

import java.io.Serializable;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import br.com.fiap.web_service.Enums.TipoUsuario;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

public class UsuarioResponse {

  public static UsuarioResponse autenticar(String email, String password,
      EntityManager em) throws Exception {
    try {
      UsuarioResponse usuario = em.createNamedQuery("Usuario.findByEmail",
          UsuarioResponse.class)
          .setParameter("email", email)
          .getSingleResult();
      if (usuario.verificaSenha(password)) {
        return usuario; // authentication successful
      } else {
        throw new Exception("Invalid password for user " + email);
      }
    } catch (Exception e) {
      throw new ResourceNotFoundException("User " + email + " not found");
    }
  }

  private Long idUsuario;

  private String nome;

  private String email;

  private String senha;

  private TipoUsuario tipoUsuario;

  private List<ReclamacaoResponse> reclamacoes;
  private List<AvaliacaoResponse> avaliacoes;
  private List<TopicoForumResponse> topicoForum;
  // private List<MensagemForum> mensagemForum;
  private List<RedeSocialResponse> redeSocial;
  private List<MensagemChatResponse> mensagemChats;

  private List<NotificacaoResponse> notificação;

  public String getSenha() {
    return senha;
  }

  public List<TopicoForumResponse> getTopicoForum() {
    return topicoForum;
  }

  public void setTopicoForum(List<TopicoForumResponse> topicoForum) {
    this.topicoForum = topicoForum;
  }

  public List<RedeSocialResponse> getRedeSocial() {
    return redeSocial;
  }

  public void setRedeSocial(List<RedeSocialResponse> redeSocial) {
    this.redeSocial = redeSocial;
  }

  public List<MensagemChatResponse> getMensagemChats() {
    return mensagemChats;
  }

  public void setMensagemChats(List<MensagemChatResponse> mensagemChats) {
    this.mensagemChats = mensagemChats;
  }

  public List<NotificacaoResponse> getNotificação() {
    return notificação;
  }

  public void setNotificação(List<NotificacaoResponse> notificação) {
    this.notificação = notificação;
  }

  public String toString() {
    return "Usuario [idUsuario=" + this.getIdUsuario() + ", nome=" + this.getNome() + ", email=" + this.getEmail()
        + ", senha=" + senha
        + ", tipoUsuario=" + this.getTipoUsuario() + "]";
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

  public List<ReclamacaoResponse> getReclamacoes() {
    return this.reclamacoes;
  }

  public void setReclamacoes(List<ReclamacaoResponse> reclamacoes) {
    this.reclamacoes = reclamacoes;
  }

  public List<AvaliacaoResponse> getAvaliacoes() {
    return this.avaliacoes;
  }

  public void setAvaliacoes(List<AvaliacaoResponse> avaliacoes) {
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
