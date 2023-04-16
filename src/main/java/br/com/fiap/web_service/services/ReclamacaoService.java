package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.Reclamacao;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import br.com.fiap.web_service.repository.ReclamacaoRepository;

@Service
public class ReclamacaoService {
  @Autowired
  private ReclamacaoRepository ReclamacaoRepository;

  public List<Reclamacao> findAll() {
    // get all Avaliations
    List<Reclamacao> Reclamacoes = ReclamacaoRepository.findAll();
    return Reclamacoes.stream().map(Reclamacao -> new ModelMapper()
        .map(Reclamacao, Reclamacao.class))
        .collect(Collectors.toList());

  }

  public Optional<Reclamacao> findById(Long id) {
    Optional<Reclamacao> Reclamacao = ReclamacaoRepository.findById(id);

    if (Reclamacao.isEmpty()) {
      throw new ResourceNotFoundException("a reclamação com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(Reclamacao.get(), Reclamacao.class));
  }

  public void deleteById(Long id) {
    Optional<Reclamacao> Reclamacao = ReclamacaoRepository.findById(id);

    if (Reclamacao.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Reclamação com o id: " + id + "- Avaliação não existe");
    }
    ReclamacaoRepository.deleteById(id);

  }

  public Reclamacao create(Reclamacao Reclamacao) {

    // delete the id from the object
    Reclamacao.setId(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    // Reclamacao Reclamacao = mapper.map(Reclamacao, Reclamacao.class);
    Reclamacao = ReclamacaoRepository.save(Reclamacao);
    Reclamacao.setId(Reclamacao.getId());
    return Reclamacao;

  }

  public Reclamacao update(Long id, Reclamacao Reclamacao) {
    Reclamacao.setId(id);
    ModelMapper mapper = new ModelMapper();
    // Reclamacao Reclamacao = mapper.map(Reclamacao, Reclamacao.class);
    ReclamacaoRepository.save(Reclamacao);
    return Reclamacao;
  }
}
