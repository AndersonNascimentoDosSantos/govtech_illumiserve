package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.PostagemRedeSocial;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;

import br.com.fiap.web_service.repository.PostagemRedeSocialRepository;

import br.com.fiap.web_service.shared.PostagemRedeSocialDTO;

@Service
public class PostagemRedeSocialService {
  @Autowired
  private PostagemRedeSocialRepository postagemRedeSocialRepository;

  public List<PostagemRedeSocialDTO> findAll() {
    // get all Avaliations
    List<PostagemRedeSocial> postagenSocials = postagemRedeSocialRepository.findAll();
    return postagenSocials.stream().map(postagemRedeSocial -> new ModelMapper()
        .map(postagemRedeSocial, PostagemRedeSocialDTO.class))
        .collect(Collectors.toList());

  }

  public Optional<PostagemRedeSocialDTO> findById(Long id) {
    Optional<PostagemRedeSocial> postagemRedeSocial = postagemRedeSocialRepository.findById(id);

    if (postagemRedeSocial.isEmpty()) {
      throw new ResourceNotFoundException("a Postagem com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(postagemRedeSocial.get(), PostagemRedeSocialDTO.class));
  }

  public void deleteById(Long id) {
    Optional<PostagemRedeSocial> postagemRedeSocial = postagemRedeSocialRepository.findById(id);

    if (postagemRedeSocial.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Postagem com o id: " + id + "- Avaliação não existe");
    }
    postagemRedeSocialRepository.deleteById(id);

  }

  public PostagemRedeSocialDTO create(PostagemRedeSocialDTO postagemRedeSocialD) {

    // delete the id from the object
    postagemRedeSocialD.setId(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    PostagemRedeSocial postagemRedeSocial = mapper.map(postagemRedeSocialD, PostagemRedeSocial.class);
    postagemRedeSocial = postagemRedeSocialRepository.save(postagemRedeSocial);
    postagemRedeSocialD.setId(postagemRedeSocial.getId());
    return postagemRedeSocialD;

  }

  public PostagemRedeSocialDTO update(Long id, PostagemRedeSocialDTO postagemRedeSocialD) {
    postagemRedeSocialD.setId(id);
    ModelMapper mapper = new ModelMapper();
    PostagemRedeSocial postagemRedeSocial = mapper.map(postagemRedeSocialD, PostagemRedeSocial.class);
    postagemRedeSocialRepository.save(postagemRedeSocial);
    return postagemRedeSocialD;
  }
}
