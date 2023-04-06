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

import br.com.fiap.web_service.services.UsuarioService;
import br.com.fiap.web_service.shared.UsuarioDTO;

import br.com.fiap.web_service.view.model.request.UsuarioRequest;
import br.com.fiap.web_service.view.model.response.UsuarioResponse;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;

  @GetMapping
  public ResponseEntity<List<UsuarioResponse>> obterTodos() {
    List<UsuarioDTO> usuarios = usuarioService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<UsuarioResponse> resposta = usuarios.stream().map(usuarioDto -> mapper
        .map(usuarioDto, UsuarioResponse.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UsuarioResponse> adicionar(@RequestBody UsuarioRequest usuarioReq) {
    ModelMapper mapper = new ModelMapper();

    UsuarioDTO usuarioDto = mapper.map(usuarioReq, UsuarioDTO.class);

    usuarioDto = usuarioService.create(usuarioDto);

    return new ResponseEntity<>(mapper.map(usuarioDto, UsuarioResponse.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<UsuarioResponse>> obterPorId(@PathVariable Long id) {

    Optional<UsuarioDTO> dto = usuarioService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), UsuarioResponse.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    usuarioService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UsuarioResponse> atualizar(@RequestBody UsuarioRequest usuarioReq, @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    UsuarioDTO UsuarioDTO = mapper.map(usuarioReq, UsuarioDTO.class);
    UsuarioDTO = usuarioService.update(id, UsuarioDTO);
    return new ResponseEntity<>(
        mapper.map(UsuarioDTO, UsuarioResponse.class), HttpStatus.OK);
  }
}
