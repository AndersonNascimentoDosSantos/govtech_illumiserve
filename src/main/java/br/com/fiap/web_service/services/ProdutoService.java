package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.Produto;
import br.com.fiap.web_service.repository.ProdutoRepository;

@Service
public class ProdutoService {
  @Autowired
  private ProdutoRepository produtoRepository;

  /**
   * Retorna lista de produtos
   * 
   * @return lista de produtos
   */
  public List<Produto> obterTodos() {
    return produtoRepository.obterTodos();
  }

  /**
   * Metodo que retorna um produto pelo id
   * 
   * @param id do produto a ser localizado
   * @return retorna um produto caso encotrado
   */
  public Optional<Produto> obterPorId(Integer id) {
    return produtoRepository.obterPorId(id);

  }

  /**
   * metodo para adicionar produto na lista
   * 
   * @param produto produto a ser adicionado no banco
   * @return retorna o produto inserido na lista
   */
  public Produto adicionar(Produto produto) {
    // acrescentar regras de validações

    return produtoRepository.adicionar(produto);
  }

  /**
   * remove item baseado no id informado se existir
   * 
   * @param id do objeto a ser removido
   */
  public void deletar(Integer id) {
    produtoRepository.deletar(id);
  }

  /**
   * metodo pra atualizar produto na lista
   * 
   * @param produto produto a ser atualizado
   * @return
   */
  public Produto atualizar(Integer id, Produto produto) {
    produto.setId(id);
    return produtoRepository.atualizar(produto);
  }
}
