package br.com.fiap.web_service.view.controller;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.web_service.model.Notificacao;
import br.com.fiap.web_service.model.Usuario;
import br.com.fiap.web_service.services.NotificacaoService;

@RestController
@RequestMapping("/api/notificacao")
public class NotificacaoController {
  @Autowired
  private NotificacaoService notificacaoService;

  @GetMapping
  public ResponseEntity<List<Notificacao>> obterTodos() {
    List<Notificacao> topicos = notificacaoService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<Notificacao> resposta = topicos.stream().map(notificacao -> mapper
        .map(notificacao, Notificacao.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping("/{idUsuario}")
  public ResponseEntity<Notificacao> adicionar(@RequestBody Notificacao topicoForumRequest,
      @PathVariable Long idUsuario) {
    ModelMapper mapper = new ModelMapper();
    Usuario usuario = new Usuario();
    usuario.setIdUsuario(idUsuario);

    Notificacao notificacao = mapper.map(topicoForumRequest, Notificacao.class);
    notificacao.setUsuario(usuario);
    notificacao = notificacaoService.create(notificacao);

    return new ResponseEntity<>(mapper.map(notificacao, Notificacao.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Notificacao>> obterPorId(@PathVariable Long id) {

    Optional<Notificacao> dto = notificacaoService.findById(id);
    return new ResponseEntity<>(
        Optional.of(new ModelMapper().map(dto.get(), Notificacao.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    notificacaoService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Notificacao> atualizar(
      @RequestBody Notificacao topicoForumRequest, @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    Notificacao notificacao = mapper.map(topicoForumRequest, Notificacao.class);
    notificacao = notificacaoService.update(id, notificacao);
    return new ResponseEntity<>(
        mapper.map(notificacao, Notificacao.class), HttpStatus.OK);
  }
}