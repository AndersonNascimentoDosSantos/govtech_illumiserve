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

import br.com.fiap.web_service.model.Usuario;
import br.com.fiap.web_service.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;

  @GetMapping
  public ResponseEntity<List<Usuario>> obterTodos() {
    List<Usuario> usuarios = usuarioService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<Usuario> resposta = usuarios.stream().map(usuario -> mapper
        .map(usuario, Usuario.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Usuario> adicionar(@RequestBody Usuario usuarioReq) {
    ModelMapper mapper = new ModelMapper();

    Usuario usuario = mapper.map(usuarioReq, Usuario.class);

    usuario = usuarioService.create(usuario);

    return new ResponseEntity<>(mapper.map(usuario, Usuario.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable Long id) {

    Optional<Usuario> dto = usuarioService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), Usuario.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    usuarioService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuarioReq, @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    Usuario usuario = mapper.map(usuarioReq, Usuario.class);
    usuario = usuarioService.update(id, usuarioReq);
    return new ResponseEntity<>(
        mapper.map(usuario, Usuario.class), HttpStatus.OK);
  }
}
