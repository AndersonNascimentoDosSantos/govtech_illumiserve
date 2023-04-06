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

import br.com.fiap.web_service.services.MensagemChatService;

import br.com.fiap.web_service.shared.MensagemChatDTO;
import br.com.fiap.web_service.view.model.request.MensagemChatRequest;
import br.com.fiap.web_service.view.model.response.MensagemChatResponse;

@RestController
@RequestMapping("/api/mensagens_chat")
public class MensagemChatController {
  @Autowired
  private MensagemChatService mensagemChatService;

  @GetMapping
  public ResponseEntity<List<MensagemChatResponse>> obterTodos() {
    List<MensagemChatDTO> mensagemChats = mensagemChatService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<MensagemChatResponse> resposta = mensagemChats.stream().map(mensagemChat -> mapper
        .map(mensagemChat, MensagemChatResponse.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MensagemChatResponse> adicionar(@RequestBody MensagemChatRequest mensagemChatReq) {
    ModelMapper mapper = new ModelMapper();

    MensagemChatDTO MensagemChatDto = mapper.map(mensagemChatReq, MensagemChatDTO.class);

    MensagemChatDto = mensagemChatService.create(MensagemChatDto);

    return new ResponseEntity<>(mapper.map(MensagemChatDto, MensagemChatResponse.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<MensagemChatResponse>> obterPorId(@PathVariable Long id) {

    Optional<MensagemChatDTO> dto = mensagemChatService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), MensagemChatResponse.class)),
        HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    mensagemChatService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MensagemChatResponse> atualizar(@RequestBody MensagemChatRequest mensagemChatReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    MensagemChatDTO mensagemChatDto = mapper.map(mensagemChatReq, MensagemChatDTO.class);
    mensagemChatDto = mensagemChatService.update(id, mensagemChatDto);
    return new ResponseEntity<>(
        mapper.map(mensagemChatDto, MensagemChatResponse.class), HttpStatus.OK);
  }
}
