package io.github.marcoantoniossilva.assets_manager.domain.model;

import java.math.BigDecimal;

public class DashboardModel {

  private BigDecimal amountInvested;

  private Double depreciatedAmount;

  private Long equipmentsCount;

  private Long companiesCount;

  public DashboardModel() {
  }

  public DashboardModel(BigDecimal amountInvested, Double depreciatedAmount,
                        Long equipmentsCount, Long companiesCount) {
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

  public Long getEquipmentsCount() {
    return equipmentsCount;
  }

  public void setEquipmentsCount(Long equipmentsCount) {
    this.equipmentsCount = equipmentsCount;
  }

  public Long getCompaniesCount() {
    return companiesCount;
  }

  public void setCompaniesCount(Long companiesCount) {
    this.companiesCount = companiesCount;
  }
}
