package io.github.marcoantoniossilva.assets_manager.api.model.input;

import io.github.marcoantoniossilva.assets_manager.api.model.BaseEntityModel;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class EquipmentInput extends BaseEntityModel {

  private Integer equipmentType;

  private Integer sector;

  private Integer company;

  private String description;

  private BigDecimal acquisitionValue;

  private LocalDate acquisitionDate;

  private MultipartFile nfe;

  private String status;

  public EquipmentInput() {
  }

  public Integer getEquipmentType() {
    return equipmentType;
  }

  public Integer getSector() {
    return sector;
  }

  public Integer getCompany() {
    return company;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getAcquisitionValue() {
    return acquisitionValue;
  }

  public LocalDate getAcquisitionDate() {
    return acquisitionDate;
  }

  public Optional<MultipartFile> getNfe() {
    return Optional.ofNullable(nfe);
  }

  public String getStatus() {
    return status;
  }

}
