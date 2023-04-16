package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.MensagemForum;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;

import br.com.fiap.web_service.repository.MensagemForumRepository;

@Service
public class MensagemForumService {
  @Autowired
  private MensagemForumRepository mensagemForumRepository;

  public List<MensagemForum> findAll() {
    // get all Avaliations
    List<MensagemForum> mensagensForum = mensagemForumRepository.findAll();
    return mensagensForum.stream().map(mensagemForum -> new ModelMapper()
        .map(mensagemForum, MensagemForum.class))
        .collect(Collectors.toList());

  }

  public Optional<MensagemForum> findById(Long id) {
    Optional<MensagemForum> mensagemForum = mensagemForumRepository.findById(id);

    if (mensagemForum.isEmpty()) {
      throw new ResourceNotFoundException("a Mensagem com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(mensagemForum.get(), MensagemForum.class));
  }

  public void deleteById(Long id) {
    Optional<MensagemForum> mensagemForum = mensagemForumRepository.findById(id);

    if (mensagemForum.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Mensagem com o id: " + id + "- Avaliação não existe");
    }
    mensagemForumRepository.deleteById(id);

  }

  public MensagemForum create(MensagemForum mensagemForum) {

    // delete the id from the object
    mensagemForum.setIdMensagem(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    // MensagemForum mensagemForum = mapper.map(mensagemForum, MensagemForum.class);
    mensagemForum = mensagemForumRepository.save(mensagemForum);
    mensagemForum.setIdMensagem(mensagemForum.getIdMensagem());
    return mensagemForum;

  }

  public MensagemForum update(Long id, MensagemForum mensagemForum) {
    mensagemForum.setIdMensagem(id);
    ModelMapper mapper = new ModelMapper();
    // MensagemForum mensagemForum = mapper.map(mensagemForum, MensagemForum.class);
    mensagemForumRepository.save(mensagemForum);
    return mensagemForum;
  }
}
