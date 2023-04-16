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

import br.com.fiap.web_service.model.TopicoForum;
import br.com.fiap.web_service.services.TopicoForumService;

@RestController
@RequestMapping("/api/topicos")
public class TopicoForumController {
  @Autowired
  private TopicoForumService topicoForumService;

  @GetMapping
  public ResponseEntity<List<TopicoForum>> obterTodos() {
    List<TopicoForum> topicos = topicoForumService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<TopicoForum> resposta = topicos.stream().map(topicoForum -> mapper
        .map(topicoForum, TopicoForum.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TopicoForum> adicionar(@RequestBody TopicoForum topicoForumReq) {
    ModelMapper mapper = new ModelMapper();

    TopicoForum topicoForum = mapper.map(topicoForumReq, TopicoForum.class);

    topicoForum = topicoForumService.create(topicoForum);

    return new ResponseEntity<>(mapper.map(topicoForum, TopicoForum.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<TopicoForum>> obterPorId(@PathVariable Long id) {

    Optional<TopicoForum> dto = topicoForumService.findById(id);
    return new ResponseEntity<>(
        Optional.of(new ModelMapper().map(dto.get(), TopicoForum.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    topicoForumService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TopicoForum> atualizar(
      @RequestBody TopicoForum topicoForum, @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    // TopicoForum topicoForum = mapper.map(topicoForum, TopicoForum.class);
    topicoForum = topicoForumService.update(id, topicoForum);
    return new ResponseEntity<>(
        mapper.map(topicoForum, TopicoForum.class), HttpStatus.OK);
  }
}