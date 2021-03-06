package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "equipment_types")
public class EquipmentType extends BaseEntity{

  private String description;

  private BigDecimal depreciation;

  @Column(name = "depreciation_term")
  private Integer depreciationTerm;

  public EquipmentType() {
  }

  public EquipmentType(String description, BigDecimal depreciation, Integer depreciationTerm) {
    this.description = description;
    this.depreciation = depreciation;
    this.depreciationTerm = depreciationTerm;
  }

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


  @Override
  public String toString() {
    return "EquipmentType{" +
        "description='" + description + '\'' +
        ", depreciation=" + depreciation +
        ", depreciationTerm=" + depreciationTerm +
        '}';
  }
}
