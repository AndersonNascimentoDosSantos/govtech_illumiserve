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

import br.com.fiap.web_service.services.EmpresaService;

import br.com.fiap.web_service.shared.EmpresaDTO;
import br.com.fiap.web_service.view.model.request.EmpresaRequest;
import br.com.fiap.web_service.view.model.response.EmpresaResponse;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
  @Autowired
  private EmpresaService empresaService;

  @GetMapping
  public ResponseEntity<List<EmpresaResponse>> obterTodos() {
    List<EmpresaDTO> empresas = empresaService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<EmpresaResponse> resposta = empresas.stream().map(empresa -> mapper
        .map(empresa, EmpresaResponse.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<EmpresaResponse> adicionar(@RequestBody EmpresaRequest empresaReq) {
    ModelMapper mapper = new ModelMapper();

    EmpresaDTO empresaDto = mapper.map(empresaReq, EmpresaDTO.class);

    empresaDto = empresaService.create(empresaDto);

    return new ResponseEntity<>(mapper.map(empresaDto, EmpresaResponse.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<EmpresaResponse>> obterPorId(@PathVariable Long id) {

    Optional<EmpresaDTO> dto = empresaService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), EmpresaResponse.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    empresaService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmpresaResponse> atualizar(@RequestBody EmpresaRequest empresaReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    EmpresaDTO EmpresaDto = mapper.map(empresaReq, EmpresaDTO.class);
    EmpresaDto = empresaService.update(id, EmpresaDto);
    return new ResponseEntity<>(
        mapper.map(EmpresaDto, EmpresaResponse.class), HttpStatus.OK);
  }
}
