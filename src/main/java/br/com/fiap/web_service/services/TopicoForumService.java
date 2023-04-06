package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.TopicoForum;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;

import br.com.fiap.web_service.repository.TopicoForumRepository;

import br.com.fiap.web_service.shared.TopicoForumDTO;

@Service
public class TopicoForumService {
  @Autowired
  private TopicoForumRepository topicoForumRepository;

  public List<TopicoForumDTO> findAll() {
    // get all Avaliations
    List<TopicoForum> mensagensForum = topicoForumRepository.findAll();
    return mensagensForum.stream().map(TopicoForum -> new ModelMapper()
        .map(TopicoForum, TopicoForumDTO.class))
        .collect(Collectors.toList());

  }

  public Optional<TopicoForumDTO> findById(Long id) {
    Optional<TopicoForum> TopicoForum = topicoForumRepository.findById(id);

    if (TopicoForum.isEmpty()) {
      throw new ResourceNotFoundException("a Topico com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(TopicoForum.get(), TopicoForumDTO.class));
  }

  public void deleteById(Long id) {
    Optional<TopicoForum> TopicoForum = topicoForumRepository.findById(id);

    if (TopicoForum.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Topico com o id: " + id + "- Avaliação não existe");
    }
    topicoForumRepository.deleteById(id);

  }

  public TopicoForumDTO create(TopicoForumDTO TopicoForumDTO) {

    // delete the id from the object
    TopicoForumDTO.setIdTopico(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    TopicoForum TopicoForum = mapper.map(TopicoForumDTO, TopicoForum.class);
    TopicoForum = topicoForumRepository.save(TopicoForum);
    TopicoForumDTO.setIdTopico(TopicoForum.getIdTopico());
    return TopicoForumDTO;

  }

  public TopicoForumDTO update(Long id, TopicoForumDTO TopicoForumDTO) {
    TopicoForumDTO.setIdTopico(id);
    ModelMapper mapper = new ModelMapper();
    TopicoForum TopicoForum = mapper.map(TopicoForumDTO, TopicoForum.class);
    topicoForumRepository.save(TopicoForum);
    return TopicoForumDTO;
  }
}
