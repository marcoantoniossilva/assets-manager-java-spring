package io.github.marcoantoniossilva.assets_manager.api.model.input;

import io.github.marcoantoniossilva.assets_manager.api.model.BaseEntityModel;
import io.github.marcoantoniossilva.assets_manager.domain.enumeration.Status;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class EquipmentInput extends BaseEntityModel {

  private Integer type;

  private Integer sector;

  private Integer company;

  private String description;

  private BigDecimal acquisitionValue;

  private LocalDate acquisitionDate;

  private MultipartFile nfe;

  private String status;

  public EquipmentInput() {
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public String toString() {
    return "EquipmentInput{" +
        "type=" + type +
        ", sector=" + sector +
        ", company=" + company +
        ", description='" + description + '\'' +
        ", acquisitionValue=" + acquisitionValue +
        ", acquisitionDate=" + acquisitionDate +
        ", nfe=" + nfe +
        ", status='" + status + '\'' +
        '}';
  }
}
