package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.Avaliacao;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import br.com.fiap.web_service.repository.AvaliacaoRepository;
import br.com.fiap.web_service.shared.AvaliacaoDTO;

@Service
public class AvaliacaoService {
  @Autowired
  private AvaliacaoRepository avaliacaoRepository;

  public List<AvaliacaoDTO> findAll() {
    // get all Avaliations
    List<Avaliacao> avaliacaos = avaliacaoRepository.findAll();
    return avaliacaos.stream().map(avaliacao -> new ModelMapper()
        .map(avaliacao, AvaliacaoDTO.class))
        .collect(Collectors.toList());

  }

  public Optional<AvaliacaoDTO> findById(Long id) {
    Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

    if (avaliacao.isEmpty()) {
      throw new ResourceNotFoundException("Avaliação com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(avaliacao.get(), AvaliacaoDTO.class));
  }

  public void deleteById(Long id) {
    Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

    if (avaliacao.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a avaliaçao com o id: " + id + "- Avaliação não existe");
    }
    avaliacaoRepository.deleteById(id);

  }

  public AvaliacaoDTO create(AvaliacaoDTO avaliciaoDto) {

    // delete the id from the object
    avaliciaoDto.setId(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    Avaliacao avaliacao = mapper.map(avaliciaoDto, Avaliacao.class);
    avaliacao = avaliacaoRepository.save(avaliacao);
    avaliciaoDto.setId(avaliacao.getId());
    return avaliciaoDto;

  }

  public AvaliacaoDTO update(Long id, AvaliacaoDTO avaliacaoDto) {
    avaliacaoDto.setId(id);
    ModelMapper mapper = new ModelMapper();
    Avaliacao avaliacao = mapper.map(avaliacaoDto, Avaliacao.class);
    avaliacaoRepository.save(avaliacao);
    return avaliacaoDto;
  }
}
