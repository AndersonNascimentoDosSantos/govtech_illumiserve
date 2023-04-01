package br.com.fiap.web_service.services;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.web_service.model.Produto;
import br.com.fiap.web_service.model.exception.ResourceNotFoundException;
import br.com.fiap.web_service.repository.ProdutoRepository;
import br.com.fiap.web_service.shared.ProdutoDTO;

@Service
public class ProdutoService {
  @Autowired
  private ProdutoRepository produtoRepository;

  /**
   * Retorna lista de produtos
   * 
   * @return lista de produtos
   */
  public List<ProdutoDTO> obterTodos() {

    List<Produto> produtos = produtoRepository.findAll();

    return produtos.stream()
        .map(produto -> new ModelMapper()
            .map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
  }

  /**
   * Metodo que retorna um produto pelo id
   * 
   * @param id do produto a ser localizado
   * @return retorna um produto caso encotrado
   */
  public Optional<ProdutoDTO> obterPorId(Integer id) {
    // obtemos optional de produto por id
    Optional<Produto> produto = produtoRepository.findById(id);

    // se nao encontrar lança, Exception
    if (produto.isEmpty()) {
      throw new ResourceNotFoundException("produto com o id: " + id + " não encontrado");
    }
    // convertendo o meu optional de produto em ProdutoDTO e devolvendo um optional
    // de ProdutoDTO
    return Optional.of(new ModelMapper().map(produto.get(), ProdutoDTO.class));

  }

  /**
   * metodo para adicionar produto na lista
   * 
   * @param produto produto a ser adicionado no banco
   * @return retorna o produto inserido na lista
   */
  public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
    // remover o id para consegui fazer o cadastro
    produtoDto.setId(null);
    // cria um objeto de mapeamento
    ModelMapper mapper = new ModelMapper();
    // converter o nosso ProdutoDTO em um Produto
    Produto produto = mapper.map(produtoDto, Produto.class);
    // salvar po Produto no banco
    produto = produtoRepository.save(produto);
    produtoDto.setId(produto.getId());
    // retornar um ProdutoDTO atualizado

    return produtoDto;
  }

  /**
   * remove item baseado no id informado se existir
   * 
   * @param id do objeto a ser removido
   */
  public void deletar(Integer id) {
    Optional<Produto> produto = produtoRepository.findById(id);
    // se nao existir lança exception
    if (produto.isEmpty()) {
      throw new ResourceNotFoundException(
          "Nao foi possivel deletar o produto com o id: " + id + "- Produto não existe");
    }

    produtoRepository.deleteById(id);
  }

  /**
   * metodo pra atualizar produto na lista
   * 
   * @param produto produto a ser atualizado
   * @return
   */
  public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
    // passar o id para o produtoDto
    produtoDto.setId(id);
    // criar objeto de mapeamento
    ModelMapper mapper = new ModelMapper();
    // converter o produtoDto em Produto
    Produto produto = mapper.map(produtoDto, Produto.class);
    // atualizar o Produto no banco de dados
    produtoRepository.save(produto);
    // retornar produtoDto atualizado
    return produtoDto;
  }
}
