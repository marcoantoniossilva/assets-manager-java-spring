package io.github.marcoantoniossilva.assets_manager.api.model;

import io.github.marcoantoniossilva.assets_manager.domain.model.enumeration.Status;
import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.model.EquipmentType;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

public class EquipmentDTO extends BaseEntityDTO {

  private String description;

  private BigDecimal acquisitionValue;

  private LocalDate acquisitionDate;

  private URI nfeUri;

  private Status status;

  private UserResumeDTO user;

  private EquipmentType equipmentType;

  private Sector sector;

  private CompanyResumeDTO company;

  public EquipmentDTO() {
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

  public URI getNfeUri() {
    return nfeUri;
  }

  public void setNfeUri(URI nfeUri) {
    this.nfeUri = nfeUri;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public UserResumeDTO getUser() {
    return user;
  }

  public void setUser(UserResumeDTO user) {
    this.user = user;
  }

  public EquipmentType getType() {
    return equipmentType;
  }

  public void setType(EquipmentType equipmentType) {
    this.equipmentType = equipmentType;
  }

  public Sector getSector() {
    return sector;
  }

  public void setSector(Sector sector) {
    this.sector = sector;
  }

  public CompanyResumeDTO getCompany() {
    return company;
  }

  public void setCompany(CompanyResumeDTO company) {
    this.company = company;
  }
}
