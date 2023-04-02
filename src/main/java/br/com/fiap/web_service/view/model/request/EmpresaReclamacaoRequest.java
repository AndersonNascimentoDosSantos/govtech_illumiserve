package br.com.fiap.web_service.view.model.request;

public class EmpresaReclamacaoRequest {

  private EmpresaRequest empresa;

  private ReclamacaoRequest reclamacao;

  public EmpresaRequest getEmpresa() {
    return empresa;
  }

  public void setEmpresa(EmpresaRequest empresa) {
    this.empresa = empresa;
  }

  public ReclamacaoRequest getReclamacao() {
    return reclamacao;
  }

  public void setReclamacao(ReclamacaoRequest reclamacao) {
    this.reclamacao = reclamacao;
  }

}
