package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "types")
public class Type {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;

  private BigDecimal depreciation;

  @Column(name = "depreciation_term")
  private Integer depreciationTerm;

  public Type() {
  }

  public Type(String description, BigDecimal depreciation, Integer depreciationTerm) {
    this.description = description;
    this.depreciation = depreciation;
    this.depreciationTerm = depreciationTerm;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
    return "Type{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", depreciation=" + depreciation +
        ", depreciationTerm=" + depreciationTerm +
        '}';
  }
}
