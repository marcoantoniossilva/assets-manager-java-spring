package io.github.marcoantoniossilva.assets_manager.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class EquipmentTypeDTO extends BaseEntityDTO {

  @NotBlank
  @Size(min = 5, max = 120)
  private String description;

  @NotNull
  @PositiveOrZero
  private BigDecimal depreciation;

  @NotNull
  @PositiveOrZero
  private Integer depreciationTerm;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getDepreciation() {
    return depreciation;
  }

  public void setDepreciation(BigDecimal depreciation) {
    this.depreciation = depreciation;
  }

  public Integer getDepreciationTerm() {
    return depreciationTerm;
  }

  public void setDepreciationTerm(Integer depreciationTerm) {
    this.depreciationTerm = depreciationTerm;
  }
}
