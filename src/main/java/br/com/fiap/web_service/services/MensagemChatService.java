package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.MensagemChat;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import br.com.fiap.web_service.repository.MensagemChatRepository;

@Service
public class MensagemChatService {
  @Autowired
  private MensagemChatRepository mensagemChatRepository;

  public List<MensagemChat> findAll() {
    // get all Avaliations
    List<MensagemChat> mensagensChat = mensagemChatRepository.findAll();
    return mensagensChat.stream().map(mensagemChat -> new ModelMapper()
        .map(mensagemChat, MensagemChat.class))
        .collect(Collectors.toList());

  }

  public Optional<MensagemChat> findById(Long id) {
    Optional<MensagemChat> mensagemChat = mensagemChatRepository.findById(id);

    if (mensagemChat.isEmpty()) {
      throw new ResourceNotFoundException("a Mensagem com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(mensagemChat.get(), MensagemChat.class));
  }

  public void deleteById(Long id) {
    Optional<MensagemChat> mensagemChat = mensagemChatRepository.findById(id);

    if (mensagemChat.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Mensagem com o id: " + id + "- Avaliação não existe");
    }
    mensagemChatRepository.deleteById(id);

  }

  public MensagemChat create(MensagemChat mensagemChat) {

    // delete the id from the object
    mensagemChat.setId(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    // MensagemChat mensagemChat = mapper.map(mensagemChat, MensagemChat.class);
    mensagemChat = mensagemChatRepository.save(mensagemChat);
    mensagemChat.setId(mensagemChat.getId());
    return mensagemChat;

  }

  public MensagemChat update(Long id, MensagemChat mensagemChat) {
    mensagemChat.setId(id);
    ModelMapper mapper = new ModelMapper();
    // MensagemChat mensagemChat = mapper.map(mensagemChat, MensagemChat.class);
    mensagemChatRepository.save(mensagemChat);
    return mensagemChat;
  }
}
