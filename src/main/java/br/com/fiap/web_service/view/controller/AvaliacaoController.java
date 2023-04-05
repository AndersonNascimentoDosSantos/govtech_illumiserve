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

import br.com.fiap.web_service.services.AvaliacaoService;

import br.com.fiap.web_service.shared.AvaliacaoDTO;
import br.com.fiap.web_service.view.model.request.AvaliacaoRequest;
import br.com.fiap.web_service.view.model.response.AvaliacaoResponse;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {
  @Autowired
  private AvaliacaoService avaliacaoService;

  @GetMapping
  public ResponseEntity<List<AvaliacaoResponse>> obterTodos() {
    List<AvaliacaoDTO> avaliacoes = avaliacaoService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<AvaliacaoResponse> resposta = avaliacoes.stream().map(avaliacao -> mapper
        .map(avaliacao, AvaliacaoResponse.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<AvaliacaoResponse> adicionar(@RequestBody AvaliacaoRequest avaliacaoReq) {
    ModelMapper mapper = new ModelMapper();

    AvaliacaoDTO avaliacaoDto = mapper.map(avaliacaoReq, AvaliacaoDTO.class);

    avaliacaoDto = avaliacaoService.create(avaliacaoDto);

    return new ResponseEntity<>(mapper.map(avaliacaoDto, AvaliacaoResponse.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<AvaliacaoResponse>> obterPorId(@PathVariable Long id) {

    Optional<AvaliacaoDTO> dto = avaliacaoService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), AvaliacaoResponse.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    avaliacaoService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AvaliacaoResponse> atualizar(@RequestBody AvaliacaoRequest avaliacaoReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    AvaliacaoDTO avaliacaoDto = mapper.map(avaliacaoReq, AvaliacaoDTO.class);
    avaliacaoDto = avaliacaoService.update(id, avaliacaoDto);
    return new ResponseEntity<>(
        mapper.map(avaliacaoDto, AvaliacaoResponse.class), HttpStatus.OK);
  }
}
