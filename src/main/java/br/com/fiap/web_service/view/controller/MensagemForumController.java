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

import br.com.fiap.web_service.services.MensagemForumService;
import br.com.fiap.web_service.shared.MensagemForumDTO;
import br.com.fiap.web_service.view.model.request.MensagemForumRequest;
import br.com.fiap.web_service.view.model.response.MensagemForumResponse;

@RestController
@RequestMapping("/api/topicos/mensagensForum")
public class MensagemForumController {
  @Autowired
  private  MensagemForumService mensagemForumService;

  @GetMapping
  public ResponseEntity<List<MensagemForumResponse>> obterTodos() {
    List<MensagemForumDTO> mensagens = mensagemForumService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<MensagemForumResponse> resposta = mensagens.stream().map(mensagemForumDto -> mapper
        .map(mensagemForumDto, MensagemForumResponse.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MensagemForumResponse> adicionar(@RequestBody MensagemForumRequest msgRequest) {
    ModelMapper mapper = new ModelMapper();

   MensagemForumDTO msgDto = mapper.map(msgRequest, MensagemForumDTO.class);

    msgDto = mensagemForumService.create(msgDto);

    return new ResponseEntity<>(mapper.map(msgDto, MensagemForumResponse.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<MensagemForumResponse>> obterPorId(@PathVariable Long id) {

    Optional<MensagemForumDTO> dto = mensagemForumService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), MensagemForumResponse.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    mensagemForumService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MensagemForumResponse> atualizar(@RequestBody MensagemForumRequest msgRequest, @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    MensagemForumDTO msgDto = mapper.map(msgRequest, MensagemForumDTO.class);
    msgDto = mensagemForumService.update(id, msgDto);
    return new ResponseEntity<>(
mapper.map(msgDto, MensagemForumResponse.class), HttpStatus.OK);
  }
}
