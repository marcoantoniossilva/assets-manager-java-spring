package io.github.marcoantoniossilva.assets_manager.api.model;

import io.github.marcoantoniossilva.assets_manager.domain.model.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyModel extends BaseEntity {

  @NotBlank
  @Size(min = 5, max = 120)
  private String corporateName;

  @NotBlank
  @Size(min = 5, max = 120)
  private String fantasyName;

  @NotBlank
  @Size(min = 18, max = 18)
  private String cnpj;

  public String getCorporateName() {
    return corporateName;
  }

  public void setCorporateName(String corporateName) {
    this.corporateName = corporateName;
  }

  public String getFantasyName() {
    return fantasyName;
  }

  public void setFantasyName(String fantasyName) {
    this.fantasyName = fantasyName;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }
}
