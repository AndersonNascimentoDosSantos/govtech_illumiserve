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

import br.com.fiap.web_service.shared.MensagemForumDTO;

@Service
public class MensagemForumService {
  @Autowired
  private MensagemForumRepository mensagemForumRepository;

  public List<MensagemForumDTO> findAll() {
    // get all Avaliations
    List<MensagemForum> mensagensForum = mensagemForumRepository.findAll();
    return mensagensForum.stream().map(mensagemForum -> new ModelMapper()
        .map(mensagemForum, MensagemForumDTO.class))
        .collect(Collectors.toList());

  }

  public Optional<MensagemForumDTO> findById(Long id) {
    Optional<MensagemForum> mensagemForum = mensagemForumRepository.findById(id);

    if (mensagemForum.isEmpty()) {
      throw new ResourceNotFoundException("a Mensagem com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(mensagemForum.get(), MensagemForumDTO.class));
  }

  public void deleteById(Long id) {
    Optional<MensagemForum> mensagemForum = mensagemForumRepository.findById(id);

    if (mensagemForum.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Mensagem com o id: " + id + "- Avaliação não existe");
    }
    mensagemForumRepository.deleteById(id);

  }

  public MensagemForumDTO create(MensagemForumDTO mensagemForumDTO) {

    // delete the id from the object
    mensagemForumDTO.setIdMensagem(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    MensagemForum mensagemForum = mapper.map(mensagemForumDTO, MensagemForum.class);
    mensagemForum = mensagemForumRepository.save(mensagemForum);
    mensagemForumDTO.setIdMensagem(mensagemForum.getIdMensagem());
    return mensagemForumDTO;

  }

  public MensagemForumDTO update(Long id, MensagemForumDTO mensagemForumDTO) {
    mensagemForumDTO.setIdMensagem(id);
    ModelMapper mapper = new ModelMapper();
    MensagemForum mensagemForum = mapper.map(mensagemForumDTO, MensagemForum.class);
    mensagemForumRepository.save(mensagemForum);
    return mensagemForumDTO;
  }
}
