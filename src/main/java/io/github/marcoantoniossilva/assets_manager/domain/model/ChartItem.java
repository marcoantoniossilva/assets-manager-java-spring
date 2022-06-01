package io.github.marcoantoniossilva.assets_manager.domain.model;

import java.math.BigDecimal;

public class ChartItem {

  private String companyName;

  private Long equipmentsCount;

  public ChartItem() {
  }

  public ChartItem(String companyName, Long equipmentsCount) {
    this.companyName = companyName;
    this.equipmentsCount = equipmentsCount;
  }

  public String getCompany() {
    return companyName;
  }

  public void setCompany(String companyName) {
    this.companyName = companyName;
  }

  public Long getEquipmentsCount() {
    return equipmentsCount;
  }

  public void setEquipmentsCount(Long equipmentsCount) {
    this.equipmentsCount = equipmentsCount;
  }
}
