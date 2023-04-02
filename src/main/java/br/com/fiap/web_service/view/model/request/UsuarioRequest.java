package br.com.fiap.web_service.view.model.request;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import br.com.fiap.web_service.Enums.TipoUsuario;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

public class UsuarioRequest {

  private String nome;

  private String email;

  private String senha;

  private TipoUsuario tipoUsuario;

  private List<ReclamacaoRequest> reclamacoes;

  private List<AvaliacaoRequest> avaliacoes;

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

  public List<ReclamacaoRequest> getReclamacoes() {
    return this.reclamacoes;
  }

  public void setReclamacoes(List<ReclamacaoRequest> reclamacoes) {
    this.reclamacoes = reclamacoes;
  }

  public List<AvaliacaoRequest> getAvaliacoes() {
    return this.avaliacoes;
  }

  public void setAvaliacoes(List<AvaliacaoRequest> avaliacoes) {
    this.avaliacoes = avaliacoes;
  }

  public void setSenha(String senha) {
    String hash = BCrypt.hashpw(senha, BCrypt.gensalt());
    this.senha = hash;
  }

  public boolean verificaSenha(String senha) {
    return BCrypt.checkpw(senha, this.senha);
  }

  public static UsuarioRequest autenticar(String email, String password, EntityManager em) throws Exception {
    try {
      UsuarioRequest usuario = em.createNamedQuery("Usuario.findByEmail", UsuarioRequest.class)
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

}
