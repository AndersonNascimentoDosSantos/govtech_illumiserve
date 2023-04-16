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

import br.com.fiap.web_service.model.MensagemForum;
import br.com.fiap.web_service.services.MensagemForumService;

@RestController
@RequestMapping("/api/topicos/mensagensForum")
public class MensagemForumController {
  @Autowired
  private MensagemForumService mensagemForumService;

  @GetMapping
  public ResponseEntity<List<MensagemForum>> obterTodos() {
    List<MensagemForum> mensagens = mensagemForumService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<MensagemForum> resposta = mensagens.stream().map(mensagemForum -> mapper
        .map(mensagemForum, MensagemForum.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MensagemForum> adicionar(@RequestBody MensagemForum msgRequest) {
    ModelMapper mapper = new ModelMapper();

    MensagemForum msgDto = mapper.map(msgRequest, MensagemForum.class);

    msgDto = mensagemForumService.create(msgDto);

    return new ResponseEntity<>(mapper.map(msgDto, MensagemForum.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<MensagemForum>> obterPorId(@PathVariable Long id) {

    Optional<MensagemForum> dto = mensagemForumService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), MensagemForum.class)),
        HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    mensagemForumService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MensagemForum> atualizar(@RequestBody MensagemForum msgRequest,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    MensagemForum msgDto = mapper.map(msgRequest, MensagemForum.class);
    msgDto = mensagemForumService.update(id, msgDto);
    return new ResponseEntity<>(
        mapper.map(msgDto, MensagemForum.class), HttpStatus.OK);
  }
}
