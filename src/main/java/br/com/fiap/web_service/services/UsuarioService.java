package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.Usuario;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import br.com.fiap.web_service.repository.UsuarioRepository;

@Service
public class UsuarioService {
  @Autowired
  private UsuarioRepository usuarioRepository;

  /**
   * Retorna lista de Usuarios
   * 
   * @return lista de Usuarios
   */
  public List<Usuario> findAll() {

    List<Usuario> Usuarios = usuarioRepository.findAll();

    return Usuarios.stream()
        .map(Usuario -> new ModelMapper()
            .map(Usuario, Usuario.class))
        .collect(Collectors.toList());
  }

  /**
   * Metodo que retorna um Usuario pelo id
   * 
   * @param id do Usuario a ser localizado
   * @return retorna um Usuario caso encotrado
   */
  public Optional<Usuario> findById(Long id) {
    // obtemos optional de Usuario por id
    Optional<Usuario> Usuario = usuarioRepository.findById(id);

    // se nao encontrar lança, Exception
    if (Usuario.isEmpty()) {
      throw new ResourceNotFoundException("Usuario com o id: " + id + " não encontrado");
    }
    // convertendo o meu optional de Usuario em Usuario e devolvendo um optional
    // de Usuario
    return Optional.of(new ModelMapper().map(Usuario.get(), Usuario.class));

  }

  /**
   * metodo para adicionar Usuario na lista
   * 
   * @param Usuario Usuario a ser adicionado no banco
   * @return retorna o Usuario inserido na lista
   */
  public Usuario create(Usuario Usuario) {
    // remover o id para consegui fazer o cadastro
    Usuario.setIdUsuario(null);
    // Usuario.setSenha(Usuario.getSenha());
    // cria um objeto de mapeamento
    ModelMapper mapper = new ModelMapper();
    // converter o nosso Usuario em um Usuario
    // Usuario Usuario = mapper.map(Usuario, Usuario.class);
    // salvar po Usuario no banco
    Usuario = usuarioRepository.save(Usuario);
    Usuario.setIdUsuario(Usuario.getIdUsuario());
    // retornar um Usuario atualizado

    return Usuario;
  }

  /**
   * remove item baseado no id informado se existir
   * 
   * @param id do objeto a ser removido
   */
  public void deleteById(Long id) {
    Optional<Usuario> Usuario = usuarioRepository.findById(id);
    // se nao existir lança exception
    if (Usuario.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar o Usuario com o id: " + id + "- Usuario não existe");
    }

    usuarioRepository.deleteById(id);
  }

  /**
   * metodo pra atualizar Usuario na lista
   * 
   * @param Usuario Usuario a ser atualizado
   * @return
   */
  public Usuario update(Long id, Usuario Usuario) {
    // passar o id para o Usuario
    Usuario.setIdUsuario(id);
    // criar objeto de mapeamento
    ModelMapper mapper = new ModelMapper();
    // converter o Usuario em Usuario
    // Usuario Usuario = mapper.map(Usuario, Usuario.class);
    // atualizar o Usuario no banco de dados
    usuarioRepository.save(Usuario);
    // retornar Usuario atualizado
    return Usuario;
  }
}
