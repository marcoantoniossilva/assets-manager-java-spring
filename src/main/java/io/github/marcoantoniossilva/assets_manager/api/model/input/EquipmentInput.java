package io.github.marcoantoniossilva.assets_manager.api.model.input;

import io.github.marcoantoniossilva.assets_manager.api.model.BaseEntityModel;
import io.github.marcoantoniossilva.assets_manager.domain.model.enumeration.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class EquipmentInput extends BaseEntityModel {

  @NotNull
  @DecimalMin(value = "1")
  private Integer equipmentType;

  @NotNull
  @DecimalMin(value = "1")
  private Integer sector;

  @NotNull
  @DecimalMin(value = "1")
  private Integer company;

  @NotBlank
  private String description;

  @NotNull
  @PositiveOrZero
  private BigDecimal acquisitionValue;

  @NotNull
  @PastOrPresent
  private LocalDate acquisitionDate;

  private MultipartFile nfe;

  @NotNull
  private Status status;

  public EquipmentInput() {
  }

  public Integer getEquipmentType() {
    return equipmentType;
  }

  public void setEquipmentType(Integer equipmentType) {
    this.equipmentType = equipmentType;
  }

  public Integer getSector() {
    return sector;
  }

  public void setSector(Integer sector) {
    this.sector = sector;
  }

  public Integer getCompany() {
    return company;
  }

  public void setCompany(Integer company) {
    this.company = company;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getAcquisitionValue() {
    return acquisitionValue;
  }

  public void setAcquisitionValue(BigDecimal acquisitionValue) {
    this.acquisitionValue = acquisitionValue;
  }

  public LocalDate getAcquisitionDate() {
    return acquisitionDate;
  }

  public void setAcquisitionDate(LocalDate acquisitionDate) {
    this.acquisitionDate = acquisitionDate;
  }

  public Optional<MultipartFile> getNfe() {
    return Optional.ofNullable(nfe);
  }

  public void setNfe(MultipartFile nfe) {
    this.nfe = nfe;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

}
