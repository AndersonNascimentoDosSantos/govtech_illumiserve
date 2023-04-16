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

import br.com.fiap.web_service.model.Avaliacao;
import br.com.fiap.web_service.model.Empresa;
import br.com.fiap.web_service.model.Usuario;
import br.com.fiap.web_service.services.AvaliacaoService;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {
  @Autowired
  private AvaliacaoService avaliacaoService;

  @GetMapping
  public ResponseEntity<List<Avaliacao>> obterTodos() {
    List<Avaliacao> avaliacoes = avaliacaoService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<Avaliacao> resposta = avaliacoes.stream().map(avaliacao -> mapper
        .map(avaliacao, Avaliacao.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  // @PostMapping
  // public ResponseEntity<Avaliacao> adicionar(@RequestBody
  // Avaliacao avaliacaoReq) {
  // ModelMapper mapper = new ModelMapper();

  // Avaliacao avaliacao = mapper.map(avaliacaoReq, Avaliacao.class);

  // avaliacao = avaliacaoService.create(avaliacao);

  // return new ResponseEntity<>(mapper.map(avaliacao,
  // Avaliacao.class), HttpStatus.CREATED);
  // }
  @PostMapping("/{idUsuario}/{idEmpresa}")
  public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao,
      @PathVariable Long idUsuario, @PathVariable Long idEmpresa) {
    ModelMapper modelMapper = new ModelMapper();
    Usuario usuario = new Usuario();
    Empresa empresa = new Empresa();
    usuario.setIdUsuario(idUsuario);
    empresa.setIdEmpresa(idEmpresa);
    // Avaliacao avaliacao = modelMapper.map(avaliacao, Avaliacao.class);

    avaliacao.setUsuario(usuario);
    avaliacao.setEmpresa(empresa);
    Avaliacao createdAvaliacao = avaliacaoService.create(avaliacao);

    // Avaliacao avaliacao = modelMapper.map(createdAvaliacao, Avaliacao.class);

    return new ResponseEntity<>(createdAvaliacao, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Avaliacao>> obterPorId(@PathVariable Long id) {

    Optional<Avaliacao> avaliacao = avaliacaoService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(avaliacao.get(), Avaliacao.class)),
        HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    avaliacaoService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Avaliacao> atualizar(@RequestBody Avaliacao avaliacaoReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    Avaliacao avaliacao = mapper.map(avaliacaoReq, Avaliacao.class);
    avaliacao = avaliacaoService.update(id, avaliacao);
    return new ResponseEntity<>(
        mapper.map(avaliacao, Avaliacao.class), HttpStatus.OK);
  }
}
