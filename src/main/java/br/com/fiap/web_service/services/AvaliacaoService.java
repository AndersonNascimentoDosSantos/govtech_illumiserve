package br.com.fiap.web_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.Avaliacao;
import br.com.fiap.web_service.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
  @Autowired
  private AvaliacaoRepository avaliacaoRepository;

  public List<Avaliacao> findAll() {
    return avaliacaoRepository.findAll();
  }

}
