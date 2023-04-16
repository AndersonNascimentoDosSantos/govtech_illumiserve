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

import br.com.fiap.web_service.model.MensagemChat;
import br.com.fiap.web_service.services.MensagemChatService;

@RestController
@RequestMapping("/api/mensagens_chat")
public class MensagemChatController {
  @Autowired
  private MensagemChatService mensagemChatService;

  @GetMapping
  public ResponseEntity<List<MensagemChat>> obterTodos() {
    List<MensagemChat> mensagemChats = mensagemChatService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<MensagemChat> resposta = mensagemChats.stream().map(mensagemChat -> mapper
        .map(mensagemChat, MensagemChat.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MensagemChat> adicionar(@RequestBody MensagemChat mensagemChatReq) {
    ModelMapper mapper = new ModelMapper();

    MensagemChat MensagemChat = mapper.map(mensagemChatReq, MensagemChat.class);

    MensagemChat = mensagemChatService.create(MensagemChat);

    return new ResponseEntity<>(mapper.map(MensagemChat, MensagemChat.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<MensagemChat>> obterPorId(@PathVariable Long id) {

    Optional<MensagemChat> dto = mensagemChatService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), MensagemChat.class)),
        HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    mensagemChatService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MensagemChat> atualizar(@RequestBody MensagemChat mensagemChatReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    MensagemChat mensagemChat = mapper.map(mensagemChatReq, MensagemChat.class);
    mensagemChat = mensagemChatService.update(id, mensagemChat);
    return new ResponseEntity<>(
        mapper.map(mensagemChat, MensagemChat.class), HttpStatus.OK);
  }
}
