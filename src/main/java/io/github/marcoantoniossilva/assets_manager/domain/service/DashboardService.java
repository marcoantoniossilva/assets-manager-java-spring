package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.model.DashboardModel;
import io.github.marcoantoniossilva.assets_manager.domain.model.Equipment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class DashboardService {

  private final EquipmentService equipmentService;
  private final CompanyService companyService;

  public DashboardService(EquipmentService equipmentService, CompanyService companyService) {
    this.equipmentService = equipmentService;
    this.companyService = companyService;
  }

  public DashboardModel get() {
    return new DashboardModel(
        amountInvested(),
        depreciatedAmount(),
        equipmentsCount(),
        companiesCount()
    );
  }

  private Long equipmentsCount() {
    return equipmentService.count();
  }

  private Long companiesCount() {
    return companyService.count();
  }

  private BigDecimal amountInvested() {
    return equipmentService.findAll().stream()
        .map(Equipment::getAcquisitionValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private Double depreciatedAmount() {
    return equipmentService.findAll().stream()
        .map(equipment -> calculateDepreciatedAmount(
            equipment.getAcquisitionValue(),
            equipment.getAcquisitionDate(),
            equipment.getEquipmentType().getDepreciation(),
            equipment.getEquipmentType().getDepreciationTerm()
        ))
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .setScale(2,RoundingMode.CEILING)
        .doubleValue();
  }

  private BigDecimal calculateDepreciatedAmount(BigDecimal acquisitionValue, LocalDate acquisitionDate,
                                                BigDecimal depreciation, Integer depreciationTerm) {

    LocalDate now = LocalDate.now();
    long years = ChronoUnit.YEARS.between(acquisitionDate, now);
    BigDecimal currentValue = acquisitionValue;

    while (years > 0 && depreciationTerm > 0) {
      currentValue = currentValue.multiply(
          ((BigDecimal.valueOf(100).subtract(depreciation)).divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING)));
      years--;
      depreciationTerm--;
    }
    return currentValue;
  }

}