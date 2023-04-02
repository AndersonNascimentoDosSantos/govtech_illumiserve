package br.com.fiap.web_service.shared;

public class EmpresaReclamacaoDTO {

  private Long idEmpresaReclamacao;

  private EmpresaDTO empresa;

  private ReclamacaoDTO reclamacao;

  public Long getIdEmpresaReclamacao() {
    return idEmpresaReclamacao;
  }

  public void setIdEmpresaReclamacao(Long idEmpresaReclamacao) {
    this.idEmpresaReclamacao = idEmpresaReclamacao;
  }

  public EmpresaDTO getEmpresa() {
    return empresa;
  }

  public void setEmpresa(EmpresaDTO empresa) {
    this.empresa = empresa;
  }

  public ReclamacaoDTO getReclamacao() {
    return reclamacao;
  }

  public void setReclamacao(ReclamacaoDTO reclamacao) {
    this.reclamacao = reclamacao;
  }

}
