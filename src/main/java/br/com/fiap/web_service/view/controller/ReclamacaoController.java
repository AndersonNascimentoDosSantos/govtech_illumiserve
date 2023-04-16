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

import br.com.fiap.web_service.model.Reclamacao;
import br.com.fiap.web_service.model.Empresa;
import br.com.fiap.web_service.model.Usuario;
import br.com.fiap.web_service.services.ReclamacaoService;

@RestController
@RequestMapping("/api/reclamacoes")
public class ReclamacaoController {
  @Autowired
  private ReclamacaoService reclamacaoService;

  @GetMapping
  public ResponseEntity<List<Reclamacao>> obterTodos() {
    List<Reclamacao> avaliacoes = reclamacaoService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<Reclamacao> resposta = avaliacoes.stream().map(Reclamacao -> mapper
        .map(Reclamacao, Reclamacao.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  // @PostMapping
  // public ResponseEntity<Reclamacao> adicionar(@RequestBody
  // Reclamacao reclamacaoReq) {
  // ModelMapper mapper = new ModelMapper();

  // Reclamacao Reclamacao = mapper.map(reclamacaoReq, Reclamacao.class);

  // Reclamacao = reclamacaoService.create(Reclamacao);

  // return new ResponseEntity<>(mapper.map(Reclamacao,
  // Reclamacao.class), HttpStatus.CREATED);
  // }
  @PostMapping("/{idUsuario}/{idEmpresa}")
  public ResponseEntity<Reclamacao> createReclamacao(@RequestBody Reclamacao reclamacao,
      @PathVariable Long idUsuario, @PathVariable Long idEmpresa) {
    ModelMapper modelMapper = new ModelMapper();
    Usuario usuario = new Usuario();
    Empresa empresa = new Empresa();
    usuario.setIdUsuario(idUsuario);
    empresa.setIdEmpresa(idEmpresa);
    // Reclamacao Reclamacao = modelMapper.map(Reclamacao, Reclamacao.class);

    reclamacao.setUsuario(usuario);
    reclamacao.setEmpresa(empresa);
    Reclamacao createdReclamacao = reclamacaoService.create(reclamacao);

    // Reclamacao Reclamacao = modelMapper.map(createdReclamacao, Reclamacao.class);

    return new ResponseEntity<>(createdReclamacao, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Reclamacao>> obterPorId(@PathVariable Long id) {

    Optional<Reclamacao> reclamacao = reclamacaoService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(reclamacao.get(), Reclamacao.class)),
        HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    reclamacaoService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Reclamacao> atualizar(@RequestBody Reclamacao reclamacaoReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    Reclamacao reclamacao = mapper.map(reclamacaoReq, Reclamacao.class);
    reclamacao = reclamacaoService.update(id, reclamacao);
    return new ResponseEntity<>(
        mapper.map(reclamacao, Reclamacao.class), HttpStatus.OK);
  }
}
