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

@Service
public class NotificacaoService {
  @Autowired
  private NotificacaoRepository notificacaoRepository;

  public List<Notificacao> findAll() {
    // get all Avaliations
    List<Notificacao> notificacoes = notificacaoRepository.findAll();
    return notificacoes.stream().map(notificacao -> new ModelMapper()
        .map(notificacao, Notificacao.class))
        .collect(Collectors.toList());

  }

  public Optional<Notificacao> findById(Long id) {
    Optional<Notificacao> notificacao = notificacaoRepository.findById(id);

    if (notificacao.isEmpty()) {
      throw new ResourceNotFoundException("a Mensagem com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(notificacao.get(), Notificacao.class));
  }

  public void deleteById(Long id) {
    Optional<Notificacao> notificacao = notificacaoRepository.findById(id);

    if (notificacao.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Mensagem com o id: " + id + "- Avaliação não existe");
    }
    notificacaoRepository.deleteById(id);

  }

  public Notificacao create(Notificacao notificacao) {

    // delete the id from the object
    notificacao.setIdNotificacao(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    // Notificacao notificacao = mapper.map(notificacao, Notificacao.class);
    notificacao = notificacaoRepository.save(notificacao);
    notificacao.setIdNotificacao(notificacao.getIdNotificacao());
    return notificacao;

  }

  public Notificacao update(Long id, Notificacao notificacao) {
    notificacao.setIdNotificacao(id);
    ModelMapper mapper = new ModelMapper();
    // Notificacao notificacao = mapper.map(notificacao, Notificacao.class);
    notificacaoRepository.save(notificacao);
    return notificacao;
  }
}
