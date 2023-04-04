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
import br.com.fiap.web_service.shared.ReclamacaoDTO;

@Service
public class ReclamacaoService {
  @Autowired
  private ReclamacaoRepository ReclamacaoRepository;

  public List<ReclamacaoDTO> findAll() {
    // get all Avaliations
    List<Reclamacao> Reclamacoes = ReclamacaoRepository.findAll();
    return Reclamacoes.stream().map(Reclamacao -> new ModelMapper()
        .map(Reclamacao, ReclamacaoDTO.class))
        .collect(Collectors.toList());

  }

  public Optional<ReclamacaoDTO> findById(Long id) {
    Optional<Reclamacao> Reclamacao = ReclamacaoRepository.findById(id);

    if (Reclamacao.isEmpty()) {
      throw new ResourceNotFoundException("a reclamação com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(Reclamacao.get(), ReclamacaoDTO.class));
  }

  public void deleteById(Long id) {
    Optional<Reclamacao> Reclamacao = ReclamacaoRepository.findById(id);

    if (Reclamacao.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Reclamação com o id: " + id + "- Avaliação não existe");
    }
    ReclamacaoRepository.deleteById(id);

  }

  public ReclamacaoDTO create(ReclamacaoDTO ReclamacaoDto) {

    // delete the id from the object
    ReclamacaoDto.setId(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    Reclamacao Reclamacao = mapper.map(ReclamacaoDto, Reclamacao.class);
    Reclamacao = ReclamacaoRepository.save(Reclamacao);
    ReclamacaoDto.setId(Reclamacao.getId());
    return ReclamacaoDto;

  }

  public ReclamacaoDTO update(Long id, ReclamacaoDTO ReclamacaoDTO) {
    ReclamacaoDTO.setId(id);
    ModelMapper mapper = new ModelMapper();
    Reclamacao Reclamacao = mapper.map(ReclamacaoDTO, Reclamacao.class);
    ReclamacaoRepository.save(Reclamacao);
    return ReclamacaoDTO;
  }
}
