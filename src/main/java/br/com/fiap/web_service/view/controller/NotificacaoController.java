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

import br.com.fiap.web_service.services.NotificacaoService;
import br.com.fiap.web_service.shared.NotificacaoDTO;
import br.com.fiap.web_service.view.model.request.NotificacaoRequest;
import br.com.fiap.web_service.view.model.response.NotificacaoResponse;

@RestController
@RequestMapping("/api/notificacao")
public class NotificacaoController {
  @Autowired
  private  NotificacaoService notificacaoService;

  @GetMapping
  public ResponseEntity<List<NotificacaoResponse>> obterTodos() {
    List<NotificacaoDTO> topicos = notificacaoService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<NotificacaoResponse> resposta = topicos.stream().map(notificacaoDto -> mapper
        .map(notificacaoDto, NotificacaoResponse.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<NotificacaoResponse> adicionar(@RequestBody NotificacaoRequest topicoForumRequest) {
    ModelMapper mapper = new ModelMapper();

   NotificacaoDTO notificacaoDto = mapper.map(topicoForumRequest, NotificacaoDTO.class);

    notificacaoDto = notificacaoService.create(notificacaoDto);

    return new ResponseEntity<>(mapper.map(notificacaoDto, NotificacaoResponse.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<NotificacaoResponse>> obterPorId(@PathVariable Long id) {

    Optional<NotificacaoDTO> dto = notificacaoService.findById(id);
    return new ResponseEntity<>(
      Optional.of(new ModelMapper().map(dto.get(), NotificacaoResponse.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    notificacaoService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<NotificacaoResponse> atualizar(
    @RequestBody NotificacaoRequest topicoForumRequest, @PathVariable Long id
    ) 
    {
    ModelMapper mapper = new ModelMapper();
    NotificacaoDTO notificacaoDto = mapper.map(topicoForumRequest, NotificacaoDTO.class);
    notificacaoDto = notificacaoService.update(id, notificacaoDto);
    return new ResponseEntity<>(
mapper.map(notificacaoDto, NotificacaoResponse.class), HttpStatus.OK);
  }
}