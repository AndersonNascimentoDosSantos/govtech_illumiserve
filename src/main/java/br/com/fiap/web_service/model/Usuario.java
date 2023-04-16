package br.com.fiap.web_service.model;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.fiap.web_service.Enums.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NoResultException;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tbl_usuario")

public class Usuario {

    public static Usuario autenticar(String email, String password, EntityManager em) throws Exception {
        try {
            Usuario usuario = em.createNamedQuery("Usuario.findByEmail", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
            if (usuario.verificaSenha(password)) {
                return usuario; // authentication successful
            } else {
                throw new Exception("Invalid password for user " + email);
            }
        } catch (NoResultException e) {
            throw new Exception("User " + email + " not found");
        }
    }

    @Id
    @SequenceGenerator(name = "usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nom_name", nullable = false)
    private String nome;
    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    @Column(name = "key_senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "enum_tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    // #avaliacoes
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference("usuario_avaliacoes")
    private List<Avaliacao> avaliacoes;

    // #notificação #verified
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference("usuario_notificacao")
    private List<Notificacao> notificacao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference("usuario_reclamacoes")
    private List<Reclamacao> reclamacoes;
    // // #topicos forum
    @OneToMany(mappedBy = "usuarioCriador", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference("usuario_topicoForum")
    private List<TopicoForum> topicoForum;

    // #rede sociais
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference("usuario_redeSocial")
    private List<RedeSocial> redeSocial;

    // #menssagens do chat
    @OneToMany(mappedBy = "remetente", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference("usuario_mensagemChats")
    private List<MensagemChat> mensagemChats;

    public List<RedeSocial> getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(List<RedeSocial> redeSocial) {
        this.redeSocial = redeSocial;
    }

    public List<Reclamacao> getReclamacoes() {
        return reclamacoes;
    }

    public void setReclamacoes(List<Reclamacao> reclamacoes) {
        this.reclamacoes = reclamacoes;
    }

    public List<TopicoForum> getTopicoForum() {
        return topicoForum;
    }

    public void setTopicoForum(List<TopicoForum> topicoForum) {
        this.topicoForum = topicoForum;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + this.getIdUsuario() + ", nome=" + this.getNome() + ", email=" + this.getEmail()
                + ", senha=" + senha
                + ", tipoUsuario=" + this.getTipoUsuario() + "]";
    }

    public String getSenha() {
        return senha;
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

    public void setSenha(String senha) {
        String hash = BCrypt.hashpw(senha, BCrypt.gensalt());
        this.senha = hash;
    }

    public boolean verificaSenha(String senha) {
        return BCrypt.checkpw(senha, this.senha);
    }

    public List<MensagemChat> getMensagemChats() {
        return mensagemChats;
    }

    public void setMensagemChats(List<MensagemChat> mensagemChats) {
        this.mensagemChats = mensagemChats;
    }

    public List<Notificacao> getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(List<Notificacao> notificacao) {
        this.notificacao = notificacao;
    }
}
