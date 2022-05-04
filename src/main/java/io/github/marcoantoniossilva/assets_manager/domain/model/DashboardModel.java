package io.github.marcoantoniossilva.assets_manager.domain.model;

import java.math.BigDecimal;

public class DashboardModel {

  private BigDecimal amountInvested;

  private Double depreciatedAmount;

  private Integer equipmentsCount;

  private Integer companiesCount;

  public DashboardModel() {
  }

  public DashboardModel(BigDecimal amountInvested, Double depreciatedAmount,
                        Integer equipmentsCount, Integer companiesCount) {
    this.amountInvested = amountInvested;
    this.depreciatedAmount = depreciatedAmount;
    this.equipmentsCount = equipmentsCount;
    this.companiesCount = companiesCount;
  }

  public BigDecimal getAmountInvested() {
    return amountInvested;
  }

  public void setAmountInvested(BigDecimal amountInvested) {
    this.amountInvested = amountInvested;
  }

  public Double getDepreciatedAmount() {
    return depreciatedAmount;
  }

  public void setDepreciatedAmount(Double depreciatedAmount) {
    this.depreciatedAmount = depreciatedAmount;
  }

  public Integer getEquipmentsCount() {
    return equipmentsCount;
  }

  public void setEquipmentsCount(Integer equipmentsCount) {
    this.equipmentsCount = equipmentsCount;
  }

  public Integer getCompaniesCount() {
    return companiesCount;
  }

  public void setCompaniesCount(Integer companiesCount) {
    this.companiesCount = companiesCount;
  }
}
