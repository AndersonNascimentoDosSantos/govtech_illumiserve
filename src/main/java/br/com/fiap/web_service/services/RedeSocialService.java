package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.RedeSocial;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;

import br.com.fiap.web_service.repository.RedeSocialRepository;

@Service
public class RedeSocialService {
  @Autowired
  private RedeSocialRepository RedeSocialRepository;

  public List<RedeSocial> findAll() {
    // get all Avaliations
    List<RedeSocial> postagenSocials = RedeSocialRepository.findAll();
    return postagenSocials.stream().map(RedeSocial -> new ModelMapper()
        .map(RedeSocial, RedeSocial.class))
        .collect(Collectors.toList());

  }

  public Optional<RedeSocial> findById(Long id) {
    Optional<RedeSocial> RedeSocial = RedeSocialRepository.findById(id);

    if (RedeSocial.isEmpty()) {
      throw new ResourceNotFoundException("a  com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(RedeSocial.get(), RedeSocial.class));
  }

  public void deleteById(Long id) {
    Optional<RedeSocial> RedeSocial = RedeSocialRepository.findById(id);

    if (RedeSocial.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a  com o id: " + id + "- Avaliação não existe");
    }
    RedeSocialRepository.deleteById(id);

  }

  public RedeSocial create(RedeSocial RedeSocialD) {

    // delete the id from the object
    RedeSocialD.setId(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    RedeSocial RedeSocial = mapper.map(RedeSocialD, RedeSocial.class);
    RedeSocial = RedeSocialRepository.save(RedeSocial);
    RedeSocialD.setId(RedeSocial.getId());
    return RedeSocialD;

  }

  public RedeSocial update(Long id, RedeSocial RedeSocialD) {
    RedeSocialD.setId(id);
    ModelMapper mapper = new ModelMapper();
    RedeSocial RedeSocial = mapper.map(RedeSocialD, RedeSocial.class);
    RedeSocialRepository.save(RedeSocial);
    return RedeSocialD;
  }
}
