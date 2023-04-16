package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.Avaliacao;
import br.com.fiap.web_service.model.Empresa;
import br.com.fiap.web_service.model.Usuario;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import br.com.fiap.web_service.repository.AvaliacaoRepository;
import br.com.fiap.web_service.repository.EmpresaRepository;
import br.com.fiap.web_service.repository.UsuarioRepository;

@Service
public class AvaliacaoService {
  @Autowired
  private AvaliacaoRepository avaliacaoRepository;
  @Autowired
  private EmpresaRepository empresaRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;

  public List<Avaliacao> findAll() {
    // get all Avaliations
    List<Avaliacao> avaliacaos = avaliacaoRepository.findAll();
    return avaliacaos.stream().map(avaliacao -> new ModelMapper()
        .map(avaliacao, Avaliacao.class))
        .collect(Collectors.toList());

  }

  public Optional<Avaliacao> findById(Long id) {
    Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

    if (avaliacao.isEmpty()) {
      throw new ResourceNotFoundException("Avaliação com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(avaliacao.get(), Avaliacao.class));
  }

  public void deleteById(Long id) {
    Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

    if (avaliacao.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a avaliaçao com o id: " + id + "- Avaliação não existe");
    }
    avaliacaoRepository.deleteById(id);

  }

  // public Avaliacao create(Avaliacao avaliciaoDto) {

  // // delete the id from the object
  // avaliciaoDto.setId(null);
  // // create a mapper object
  // ModelMapper mapper = new ModelMapper();

  // Avaliacao avaliacao = mapper.map(avaliciaoDto, Avaliacao.class);
  // avaliacao = avaliacaoRepository.save(avaliacao);
  // avaliciaoDto.setId(avaliacao.getId());
  // return avaliciaoDto;

  // }
  public Avaliacao create(Avaliacao avaliacao) {
    ModelMapper modelMapper = new ModelMapper();
    // Avaliacao avaliacao = modelMapper.map(avaliacao, Avaliacao.class);

    Usuario usuario = usuarioRepository.findById(avaliacao.getUsuario().getIdUsuario())
        .orElseThrow(
            () -> new ResourceNotFoundException(
                "Usuário não encontrado com ID: " + avaliacao.getUsuario().getIdUsuario()));

    Empresa empresa = empresaRepository.findById(avaliacao.getEmpresa().getIdEmpresa())
        .orElseThrow(
            () -> new ResourceNotFoundException(
                "Empresa não encontrada com ID: " + avaliacao.getEmpresa().getIdEmpresa()));

    avaliacao.setUsuario(modelMapper.map(modelMapper.map(usuario, Usuario.class), Usuario.class));
    avaliacao.setEmpresa(modelMapper.map(modelMapper.map(empresa, Empresa.class), Empresa.class));

    Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);

    return modelMapper.map(savedAvaliacao, Avaliacao.class);
  }

  public Avaliacao update(Long id, Avaliacao avaliacao) {
    avaliacao.setId(id);
    ModelMapper mapper = new ModelMapper();
    // Avaliacao avaliacao = mapper.map(avaliacao, Avaliacao.class);
    avaliacaoRepository.save(avaliacao);
    return avaliacao;
  }
}
