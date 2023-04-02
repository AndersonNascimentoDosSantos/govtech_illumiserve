package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.EmpresaReclamacao;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import br.com.fiap.web_service.repository.EmpresaReclamacaoRepository;
import br.com.fiap.web_service.shared.EmpresaReclamacaoDTO;

@Service
public class EmpresaReclamacaoService {
  @Autowired
  private EmpresaReclamacaoRepository empresaReclamacaoRepository;

  public List<EmpresaReclamacaoDTO> findAll() {
    // get all Avaliations
    List<EmpresaReclamacao> empresaReclamacoes = empresaReclamacaoRepository.findAll();
    return empresaReclamacoes.stream().map(empresaReclamacao -> new ModelMapper()
        .map(empresaReclamacao, EmpresaReclamacaoDTO.class))
        .collect(Collectors.toList());

  }

  public Optional<EmpresaReclamacaoDTO> findById(Long id) {
    Optional<EmpresaReclamacao> empresaReclamacao = empresaReclamacaoRepository.findById(id);

    if (empresaReclamacao.isEmpty()) {
      throw new ResourceNotFoundException("a reclamação com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(empresaReclamacao.get(), EmpresaReclamacaoDTO.class));
  }

  public void deleteById(Long id) {
    Optional<EmpresaReclamacao> empresaReclamacao = empresaReclamacaoRepository.findById(id);

    if (empresaReclamacao.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Reclamação com o id: " + id + "- Avaliação não existe");
    }
    empresaReclamacaoRepository.deleteById(id);

  }

  public EmpresaReclamacaoDTO create(EmpresaReclamacaoDTO empresaReclamacaoDto) {

    // delete the id from the object
    empresaReclamacaoDto.setIdEmpresaReclamacao(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    EmpresaReclamacao avaliacao = mapper.map(empresaReclamacaoDto, EmpresaReclamacao.class);
    avaliacao = empresaReclamacaoRepository.save(avaliacao);
    empresaReclamacaoDto.setIdEmpresaReclamacao(avaliacao.getIdEmpresaReclamacao());
    return empresaReclamacaoDto;

  }

  public EmpresaReclamacaoDTO update(Long id, EmpresaReclamacaoDTO avaliacaoDto) {
    avaliacaoDto.setIdEmpresaReclamacao(id);
    ModelMapper mapper = new ModelMapper();
    EmpresaReclamacao avaliacao = mapper.map(avaliacaoDto, EmpresaReclamacao.class);
    empresaReclamacaoRepository.save(avaliacao);
    return avaliacaoDto;
  }
}
