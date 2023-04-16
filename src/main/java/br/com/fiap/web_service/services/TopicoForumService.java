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

@Service
public class TopicoForumService {
  @Autowired
  private TopicoForumRepository topicoForumRepository;

  public List<TopicoForum> findAll() {
    // get all Avaliations
    List<TopicoForum> mensagensForum = topicoForumRepository.findAll();
    return mensagensForum.stream().map(TopicoForum -> new ModelMapper()
        .map(TopicoForum, TopicoForum.class))
        .collect(Collectors.toList());

  }

  public Optional<TopicoForum> findById(Long id) {
    Optional<TopicoForum> TopicoForum = topicoForumRepository.findById(id);

    if (TopicoForum.isEmpty()) {
      throw new ResourceNotFoundException("a Topico com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(TopicoForum.get(), TopicoForum.class));
  }

  public void deleteById(Long id) {
    Optional<TopicoForum> TopicoForum = topicoForumRepository.findById(id);

    if (TopicoForum.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Topico com o id: " + id + "- Avaliação não existe");
    }
    topicoForumRepository.deleteById(id);

  }

  public TopicoForum create(TopicoForum TopicoForum) {

    // delete the id from the object
    TopicoForum.setIdTopico(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    // TopicoForum TopicoForum = mapper.map(TopicoForum, TopicoForum.class);
    TopicoForum = topicoForumRepository.save(TopicoForum);
    TopicoForum.setIdTopico(TopicoForum.getIdTopico());
    return TopicoForum;

  }

  public TopicoForum update(Long id, TopicoForum TopicoForum) {
    TopicoForum.setIdTopico(id);
    ModelMapper mapper = new ModelMapper();
    // TopicoForum TopicoForum = mapper.map(TopicoForum, TopicoForum.class);
    topicoForumRepository.save(TopicoForum);
    return TopicoForum;
  }
}
