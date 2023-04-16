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

import br.com.fiap.web_service.model.PostagemRedeSocial;
import br.com.fiap.web_service.model.Reclamacao;
import br.com.fiap.web_service.model.RedeSocial;
import br.com.fiap.web_service.model.Usuario;
import br.com.fiap.web_service.services.PostagemRedeSocialService;

@RestController
@RequestMapping("/api/post-rede-social")

public class PostagemRedeSocialController {

  @Autowired
  private PostagemRedeSocialService PostagemredeSocialService;

  @GetMapping()

  public ResponseEntity<List<PostagemRedeSocial>> obterTodos() {

    List<PostagemRedeSocial> reclamacoes = PostagemredeSocialService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<PostagemRedeSocial> resposta = reclamacoes.stream().map(PostagemRedeSocial -> mapper
        .map(PostagemRedeSocial, PostagemRedeSocial.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping("/{idRedeSocial}/{idReclamacao}")
  public ResponseEntity<PostagemRedeSocial> adicionar(@RequestBody PostagemRedeSocial PostagemredeSocialReq,
      @PathVariable Long idRedeSocial, @PathVariable Long idReclamacao) {
    ModelMapper mapper = new ModelMapper();
    Reclamacao reclamacao = new Reclamacao();
    RedeSocial redeSocial = new RedeSocial();
    redeSocial.setId(idRedeSocial);
    reclamacao.setId(idReclamacao);
    PostagemRedeSocial postagemRedeSocial = mapper.map(PostagemredeSocialReq, PostagemRedeSocial.class);
    postagemRedeSocial.setRedeSocial(redeSocial);
    postagemRedeSocial.setReclamacao(reclamacao);

    postagemRedeSocial = PostagemredeSocialService.create(postagemRedeSocial);

    return new ResponseEntity<>(mapper.map(postagemRedeSocial, PostagemRedeSocial.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<PostagemRedeSocial>> obterPorId(@PathVariable Long id) {

    Optional<PostagemRedeSocial> dto = PostagemredeSocialService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), PostagemRedeSocial.class)),
        HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    PostagemredeSocialService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostagemRedeSocial> atualizar(@RequestBody PostagemRedeSocial PostagemredeSocialReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    PostagemRedeSocial PostagemRedeSocial = mapper.map(PostagemredeSocialReq, PostagemRedeSocial.class);
    PostagemRedeSocial = PostagemredeSocialService.update(id, PostagemRedeSocial);
    return new ResponseEntity<>(
        mapper.map(PostagemRedeSocial, PostagemRedeSocial.class), HttpStatus.OK);
  }

}
