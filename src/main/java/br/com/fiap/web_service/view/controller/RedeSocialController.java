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

import br.com.fiap.web_service.model.Empresa;
import br.com.fiap.web_service.model.RedeSocial;
import br.com.fiap.web_service.model.Usuario;
import br.com.fiap.web_service.services.RedeSocialService;

@RestController
@RequestMapping("/api/rede-social")

public class RedeSocialController {

  @Autowired
  private RedeSocialService redeSocialService;

  @GetMapping
  public ResponseEntity<List<RedeSocial>> obterTodos() {
    List<RedeSocial> reclamacoes = redeSocialService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<RedeSocial> resposta = reclamacoes.stream().map(RedeSocial -> mapper
        .map(RedeSocial, RedeSocial.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping("/{idUsuario}")
  public ResponseEntity<RedeSocial> adicionar(@RequestBody RedeSocial redeSocialReq, @PathVariable Long idUsuario) {
    ModelMapper mapper = new ModelMapper();
    Usuario usuario = new Usuario();
    usuario.setIdUsuario(idUsuario);
    RedeSocial RedeSocial = mapper.map(redeSocialReq, RedeSocial.class);
    RedeSocial.setUsuario(usuario);
    RedeSocial = redeSocialService.create(RedeSocial);

    return new ResponseEntity<>(mapper.map(RedeSocial, RedeSocial.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<RedeSocial>> obterPorId(@PathVariable Long id) {

    Optional<RedeSocial> dto = redeSocialService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), RedeSocial.class)),
        HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    redeSocialService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RedeSocial> atualizar(@RequestBody RedeSocial redeSocialReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    RedeSocial RedeSocial = mapper.map(redeSocialReq, RedeSocial.class);
    RedeSocial = redeSocialService.update(id, RedeSocial);
    return new ResponseEntity<>(
        mapper.map(RedeSocial, RedeSocial.class), HttpStatus.OK);
  }

}
