package br.com.fiap.web_service.view.model.response;

public class EmpresaReclamacaoResponse {

  private Long idEmpresaReclamacao;

  private EmpresaResponse empresa;

  private ReclamacaoResponse reclamacao;

  public Long getIdEmpresaReclamacao() {
    return idEmpresaReclamacao;
  }

  public void setIdEmpresaReclamacao(Long idEmpresaReclamacao) {
    this.idEmpresaReclamacao = idEmpresaReclamacao;
  }

  public EmpresaResponse getEmpresa() {
    return empresa;
  }

  public void setEmpresa(EmpresaResponse empresa) {
    this.empresa = empresa;
  }

  public ReclamacaoResponse getReclamacao() {
    return reclamacao;
  }

  public void setReclamacao(ReclamacaoResponse reclamacao) {
    this.reclamacao = reclamacao;
  }

}
