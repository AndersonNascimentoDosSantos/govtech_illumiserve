package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.Notificacao;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;

import br.com.fiap.web_service.repository.NotificacaoRepository;

import br.com.fiap.web_service.shared.NotificacaoDTO;

@Service
public class NotificacaoService {
  @Autowired
  private NotificacaoRepository notificacaoRepository;

  public List<NotificacaoDTO> findAll() {
    // get all Avaliations
    List<Notificacao> notificacoes = notificacaoRepository.findAll();
    return notificacoes.stream().map(notificacao -> new ModelMapper()
        .map(notificacao, NotificacaoDTO.class))
        .collect(Collectors.toList());

  }

  public Optional<NotificacaoDTO> findById(Long id) {
    Optional<Notificacao> notificacao = notificacaoRepository.findById(id);

    if (notificacao.isEmpty()) {
      throw new ResourceNotFoundException("a Mensagem com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(notificacao.get(), NotificacaoDTO.class));
  }

  public void deleteById(Long id) {
    Optional<Notificacao> notificacao = notificacaoRepository.findById(id);

    if (notificacao.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Mensagem com o id: " + id + "- Avaliação não existe");
    }
    notificacaoRepository.deleteById(id);

  }

  public NotificacaoDTO create(NotificacaoDTO notificacaoDto) {

    // delete the id from the object
    notificacaoDto.setIdNotificacao(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    Notificacao notificacao = mapper.map(notificacaoDto, Notificacao.class);
    notificacao = notificacaoRepository.save(notificacao);
    notificacaoDto.setIdNotificacao(notificacao.getIdNotificacao());
    return notificacaoDto;

  }

  public NotificacaoDTO update(Long id, NotificacaoDTO notificacaoDto) {
    notificacaoDto.setIdNotificacao(id);
    ModelMapper mapper = new ModelMapper();
    Notificacao notificacao = mapper.map(notificacaoDto, Notificacao.class);
    notificacaoRepository.save(notificacao);
    return notificacaoDto;
  }
}
