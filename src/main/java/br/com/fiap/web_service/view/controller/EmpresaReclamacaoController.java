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

import br.com.fiap.web_service.model.EmpresaReclamacao;
import br.com.fiap.web_service.services.EmpresaReclamacaoService;
import br.com.fiap.web_service.shared.AvaliacaoDTO;
import br.com.fiap.web_service.shared.EmpresaReclamacaoDTO;
import br.com.fiap.web_service.view.model.request.AvaliacaoRequest;
import br.com.fiap.web_service.view.model.response.EmpresaReclamacaoResponse;

@RestController
@RequestMapping("/api/empresas_reclamacoes")
public class EmpresaReclamacaoController {
  @Autowired
  private EmpresaReclamacaoService empresaReclamacaoService;

  @GetMapping
  public ResponseEntity<List<EmpresaReclamacaoResponse>> obterTodos() {
    List<EmpresaReclamacaoDTO> reclamacoes = empresaReclamacaoService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<EmpresaReclamacaoResponse> resposta = reclamacoes.stream().map(reclamacao -> mapper
        .map(reclamacao, EmpresaReclamacaoResponse.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<EmpresaReclamacaoResponse> adicionar(@RequestBody AvaliacaoRequest avaliacaoReq) {
    ModelMapper mapper = new ModelMapper();

    EmpresaReclamacaoDTO empresaReclamacaoDto = mapper.map(avaliacaoReq, EmpresaReclamacaoDTO.class);

    empresaReclamacaoDto = empresaReclamacaoService.create(empresaReclamacaoDto);

    return new ResponseEntity<>(mapper.map(empresaReclamacaoDto, EmpresaReclamacaoResponse.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<EmpresaReclamacaoResponse>> obterPorId(@PathVariable Long id) {

    Optional<EmpresaReclamacaoDTO> dto = empresaReclamacaoService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), EmpresaReclamacaoResponse.class)),
        HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    empresaReclamacaoService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmpresaReclamacaoResponse> atualizar(@RequestBody AvaliacaoRequest avaliacaoReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    EmpresaReclamacaoDTO empresaReclamacaoDto = mapper.map(avaliacaoReq, EmpresaReclamacaoDTO.class);
    empresaReclamacaoDto = empresaReclamacaoService.update(id, empresaReclamacaoDto);
    return new ResponseEntity<>(
        mapper.map(empresaReclamacaoDto, EmpresaReclamacaoResponse.class), HttpStatus.OK);
  }
}
