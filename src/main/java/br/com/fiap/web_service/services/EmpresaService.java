package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.Empresa;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import br.com.fiap.web_service.repository.EmpresaRepository;
import br.com.fiap.web_service.shared.EmpresaDTO;

@Service
public class EmpresaService {
  @Autowired
  private EmpresaRepository empresaRepository;

  public List<EmpresaDTO> findAll() {
    // get all Avaliations
    List<Empresa> empresas = empresaRepository.findAll();
    return empresas.stream().map(empresa -> new ModelMapper()
        .map(empresa, EmpresaDTO.class))
        .collect(Collectors.toList());

  }

  public Optional<EmpresaDTO> findById(Long id) {
    Optional<Empresa> empresa = empresaRepository.findById(id);

    if (empresa.isEmpty()) {
      throw new ResourceNotFoundException("a reclamação com o id: " + id + " não encontrada");
    }
    return Optional.of(new ModelMapper().map(empresa.get(), EmpresaDTO.class));
  }

  public void deleteById(Long id) {
    Optional<Empresa> empresa = empresaRepository.findById(id);

    if (empresa.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar a Reclamação com o id: " + id + "- Avaliação não existe");
    }
    empresaRepository.deleteById(id);

  }

  public EmpresaDTO create(EmpresaDTO empresaDto) {

    // delete the id from the object
    empresaDto.setIdEmpresa(null);
    // create a mapper object
    ModelMapper mapper = new ModelMapper();

    Empresa empresa = mapper.map(empresaDto, Empresa.class);
    empresa = empresaRepository.save(empresa);
    empresaDto.setIdEmpresa(empresa.getIdEmpresa());
    return empresaDto;

  }

  public EmpresaDTO update(Long id, EmpresaDTO empresaDto) {
    empresaDto.setIdEmpresa(id);
    ModelMapper mapper = new ModelMapper();
    Empresa empresa = mapper.map(empresaDto, Empresa.class);
    empresaRepository.save(empresa);
    return empresaDto;
  }
}
