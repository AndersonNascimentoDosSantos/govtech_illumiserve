package br.com.fiap.web_service.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.fiap.web_service.model.Produto;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository {
  private List<Produto> produtos = new ArrayList<Produto>();
  private Integer ultimoId = 0;

  /**
   * Retorna lista de produtos
   * 
   * @return lista de produtos
   */
  public List<Produto> obterTodos() {
    return produtos;
  }

  /**
   * Metodo que retorna um produto pelo id
   * 
   * @param id do produto a ser localizado
   * @return retorna um produto caso encotrado
   */
  public Optional<Produto> obterPorId(Integer id) {

    return produtos
        .stream()
        .filter(produto -> produto.getId() == id).findFirst();
  }

  /**
   * metodo para adicionar produto na lista
   * 
   * @param produto produto a ser adicionado no banco
   * @return retorna o produto inserido na lista
   */
  public Produto adicionar(Produto produto) {
    ultimoId++;
    produto.setId(ultimoId);
    produtos.add(produto);
    return produto;
  }

  /**
   * remove item baseado no id informado se existir
   * 
   * @param id do objeto a ser removido
   */
  public void deletar(Integer id) {
    produtos.removeIf(produto -> produto.getId() == id);
  }

  /**
   * metodo pra atualizar produto na lista
   * 
   * @param produto produto a ser atualizado
   * @return
   */
  public Produto atualizar(Produto produto) {
    Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
    if (produtoEncontrado.isEmpty()) {
      throw new ResourceNotFoundException("produto nao encontrado");
    }
    deletar(produto.getId());
    produtos.add(produto);
    return produto;
  }
}
