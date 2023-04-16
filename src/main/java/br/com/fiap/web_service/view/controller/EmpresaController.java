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
import br.com.fiap.web_service.services.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
  @Autowired
  private EmpresaService empresaService;

  @GetMapping
  public ResponseEntity<List<Empresa>> obterTodos() {
    List<Empresa> empresas = empresaService.findAll();
    ModelMapper mapper = new ModelMapper();
    List<Empresa> resposta = empresas.stream().map(empresa -> mapper
        .map(empresa, Empresa.class))
        .collect(Collectors.toList());
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Empresa> adicionar(@RequestBody Empresa empresaReq) {
    ModelMapper mapper = new ModelMapper();

    Empresa empresa = mapper.map(empresaReq, Empresa.class);

    empresa = empresaService.create(empresa);

    return new ResponseEntity<>(mapper.map(empresa, Empresa.class), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Empresa>> obterPorId(@PathVariable Long id) {

    Optional<Empresa> dto = empresaService.findById(id);
    return new ResponseEntity<>(Optional.of(new ModelMapper().map(dto.get(), Empresa.class)), HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    empresaService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Empresa> atualizar(@RequestBody Empresa empresaReq,
      @PathVariable Long id) {
    ModelMapper mapper = new ModelMapper();
    Empresa Empresa = mapper.map(empresaReq, Empresa.class);
    Empresa = empresaService.update(id, Empresa);
    return new ResponseEntity<>(
        mapper.map(Empresa, Empresa.class), HttpStatus.OK);
  }
}
