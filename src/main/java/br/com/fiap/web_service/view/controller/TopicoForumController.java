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

import br.com.fiap.web_service.services.TopicoForumService;
import br.com.fiap.web_service.shared.TopicoForumDTO;
import br.com.fiap.web_service.view.model.request.TopicoForumRequest;
import br.com.fiap.web_service.view.model.response.TopicoForumResponse;

@RestController
@RequestMapping("/api/topicos")
public class TopicoForumController{
  @Autowired
  private  TopicoForumService topicoForumService;

  @GetMapping
  public ResponseEntity<List<TopicoForumResponse>> obterTodos() {
    List<TopicoForumDTO> topicos = topicoForumService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<TopicoForumResponse> resposta = topicos.stream().map(topicoForumDto -> mapper
        .map(topicoForumDto, TopicoForumResponse.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TopicoForumResponse> adicionar(@RequestBody TopicoForumRequest topicoForumRequest) {
    ModelMapper mapper = new ModelMapper();

   TopicoForumDTO topicoForumDto = mapper.map(topicoForumRequest, TopicoForumDTO.class);

    topicoForumDto = topicoForumService.create(topicoForumDto);

    return new ResponseEntity<>(mapper.map(topicoForumDto, TopicoForumResponse.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<TopicoForumResponse>> obterPorId(@PathVariable Long id) {

    Optional<TopicoForumDTO> dto = topicoForumService.findById(id);
    return new ResponseEntity<>(
      Optional.of(new ModelMapper().map(dto.get(), TopicoForumResponse.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    topicoForumService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TopicoForumResponse> atualizar(
    @RequestBody TopicoForumRequest topicoForumRequest, @PathVariable Long id
    ) 
    {
    ModelMapper mapper = new ModelMapper();
    TopicoForumDTO topicoForumDto = mapper.map(topicoForumRequest, TopicoForumDTO.class);
    topicoForumDto = topicoForumService.update(id, topicoForumDto);
    return new ResponseEntity<>(
mapper.map(topicoForumDto, TopicoForumResponse.class), HttpStatus.OK);
  }
}