package br.com.fiap.web_service.shared;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import br.com.fiap.web_service.Enums.TipoUsuario;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

public class UsuarioDTO {

  private Long idUsuario;

  private String nome;

  private String email;

  private String senha;

  private TipoUsuario tipoUsuario;

  private List<ReclamacaoDTO> reclamacoes;

  private List<AvaliacaoDTO> avaliacoes;

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

  public static UsuarioDTO autenticar(String email, String password, EntityManager em) throws Exception {
    try {
      UsuarioDTO usuario = em.createNamedQuery("Usuario.findByEmail", UsuarioDTO.class)
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
